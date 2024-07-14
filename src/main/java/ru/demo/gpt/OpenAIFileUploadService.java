package ru.demo.gpt;

import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class OpenAIFileUploadService {

    @Value("${OpenAi.apiKey}")
    private String apiKey;

    private final String UPLOAD_URL = "https://api.openai.com/v1/files";

    public void sendFile() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Подготовка файла и данных формы
        File file = new File("C:\\Users\\ProkopenkoA\\IdeaProjects\\test_gpt1\\src\\main\\resources\\test.jsonl");
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
                .addHeader("Authorization", "Bearer " + apiKey)
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
