package ru.demo.gpt;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Prokopenko Andrey
 * @since 14.07.2024
 */
@Service
public class UploadService {

    @Value("${OpenAi.apiKey}")
    private String apiKey;

    public void uploadFile() throws IOException {
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("input_file_id", "file-adGKuJETWGeRgjOBjCqG1Qgd");
//        jsonObject.put("endpoint", "/v1/chat/completions");
//        jsonObject.put("completion_window", "24h");

        URL url = new URL("https://api.openai.com/v1/files/file-MWGvBcpV64vkZlNVnZyNsbVI/content");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);


//        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
////        writer.write(jsonObject.toString());
//        writer.flush();
//        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println(response.toString());
    }
}
