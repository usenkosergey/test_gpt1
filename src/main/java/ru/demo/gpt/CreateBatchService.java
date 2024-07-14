package ru.demo.gpt;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author Prokopenko Andrey
 * @since 14.07.2024
 */
@Service
public class CreateBatchService {

    @Value("${OpenAi.apiKey}")
    private String apiKey;

    public void createBatch() throws IOException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("input_file_id", "file-adGKuJETWGeRgjOBjCqG1Qgd");
        jsonObject.put("endpoint", "/v1/chat/completions");
        jsonObject.put("completion_window", "24h");

        URL url = new URL("https://api.openai.com/v1/batches");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println(response.toString());

//        JSONObject responseObject = new JSONObject(response);
//        JSONArray choices = responseObject.getJSONArray("choices");
//        JSONObject messageObject = choices.getJSONObject(0).getJSONObject("message"); //todo избавиться от индекса 0
//        String content = messageObject.getString("content");
//        return content;
    }
}
