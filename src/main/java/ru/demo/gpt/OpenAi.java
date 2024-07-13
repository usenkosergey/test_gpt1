package ru.demo.gpt;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Usenko Sergey, 29.06.2024
 */
public class OpenAi {
    public static void main(String[] args) {
        chatGPT("test");
    }

    public static String chatGPT(String massage) {
        String urlOi = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";
        String apiKey = "";

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("model", model);
            jsonObject.put("temperature", 1);

            JSONObject j01 = new JSONObject();
            j01.put("role", "system");
            j01.put("content","Тебя зовут Вова");


            JSONObject j1 = new JSONObject();
            j1.put("role", "user");
            j1.put("content","Привет, как тебя зовут?");

            JSONObject j2 = new JSONObject();
            j2.put("role", "assistant");
            j2.put("content","Привет! Меня зовут Вова. Чем могу помочь?");

            JSONObject j3 = new JSONObject();
            j3.put("role", "user");
            j3.put("content","Какой у тебя пол?");


            JSONArray a = new JSONArray();
            a.put(j01);
            a.put(j1);
            a.put(j2);
            a.put(j3);

            jsonObject.put("messages", a);

//            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"Привет, можешь мне помочь?\"}],\"temperature\": 0.7}";
            String body = jsonObject.toString();
            System.out.println(body);
            URL url = new URL(urlOi);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonObject1 = new JSONObject(response.toString());
            JSONArray array = jsonObject1.getJSONArray("choices");
            JSONObject jsonObject11 = array.getJSONObject(0);
            JSONObject jsonObject2 = jsonObject11.getJSONObject("message");
            String content = jsonObject2.getString("content");
            System.out.println(content);

            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
