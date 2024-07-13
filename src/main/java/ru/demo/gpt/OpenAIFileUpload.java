package ru.demo.gpt;

import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class OpenAIFileUpload {


    private static final String API_KEY = "";
    private static final String UPLOAD_URL = "https://api.openai.com/v1/files";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Подготовка файла и данных формы
        File file = new File("f:\\11\\test.jsonl");
        RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/jsonl"));
        RequestBody formBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("purpose", "batch")
                .build();

        // Подготовка запроса
        Request request = new Request.Builder()
                .url(UPLOAD_URL)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        // Выполнение запроса
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Чтение ответа
            JSONObject jsonResponse = new JSONObject(response.body().string());
            System.out.println(jsonResponse.toString(2));
        }
    }
}
