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
        String apiKey = "";
        String model = "gpt-3.5-turbo";

        try {

            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"Привет, можешь мне помочь?\"}],\"temperature\": 0.7}";
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
            JSONObject jsonObject = array.getJSONObject(0);
            JSONObject jsonObject2 = jsonObject.getJSONObject("message");
            String content = jsonObject2.getString("content");
            System.out.println(content);

            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
