package ru.demo.gpt;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Usenko Sergey, 29.06.2024
 */
@Service
public class OpenAi {

    @Value("${OpenAi.apiKey}")
    String apiKey;

    public void chatGPT() {
        System.out.println(apiKey);
        String urlOi = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";
//        String apiKey = ;

        try(Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
            JSONObject jsonObject = getJsonObject(model);
            JSONObject promptMessage = getPromptMessage(scanner);
            JSONArray messages = new JSONArray();
            messages.put(promptMessage);

            while (true) {
                JSONObject userMessage = new JSONObject();
                userMessage.put("role", "user");
                System.out.print("Your question: ");
                userMessage.put("content", scanner.nextLine());
                messages.put(userMessage);
                jsonObject.put("messages", messages);
                String body = jsonObject.toString();

                String content = getAnswerFromChatGpt(urlOi, apiKey, body);
                System.out.print("ChatGpt answer: ");
                System.out.println(content);
                JSONObject assistantMessage = new JSONObject();
                assistantMessage.put("role", "assistant");
                assistantMessage.put("content", content);
                messages.put(assistantMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull JSONObject getJsonObject(String model) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", model);
        jsonObject.put("temperature", 1);
        return jsonObject;
    }

    private @NotNull JSONObject getPromptMessage(Scanner scanner) {
        JSONObject promptMessage = new JSONObject();
        promptMessage.put("role", "system");
        System.out.print("Enter prompt message: ");
        promptMessage.put("content", scanner.nextLine());
        return promptMessage;
    }

    private String getAnswerFromChatGpt(String urlOi, String apiKey, String body) throws IOException {
        URL url = new URL(urlOi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);

        sendRequestToChatGpt(body, con);
        String response = getResponseFromChatGpt(con);

        JSONObject responseObject = new JSONObject(response);
        JSONArray choices = responseObject.getJSONArray("choices");
        JSONObject messageObject = choices.getJSONObject(0).getJSONObject("message"); //todo избавиться от индекса 0
        String content = messageObject.getString("content");
        return content;
    }

    private @NotNull String getResponseFromChatGpt(HttpURLConnection con) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    private void sendRequestToChatGpt(String body, HttpURLConnection con) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(body);
        writer.flush();
        writer.close();
    }
}
