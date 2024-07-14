package ru.demo.gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author Prokopenko Andrey
 * @since 14.07.2024
 */
@Service
public class RetrieveBatchService {

    @Value("${OpenAi.apiKey}")
    private String apiKey;

    public void retrieveBatch() throws IOException {
//        URL url = new URL("https://api.openai.com/v1/batches/batch_qBu2IJndJgKBw39PndIEOf7t");
        URL url = new URL("https://api.openai.com/v1/files");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);

//        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
////        writer.flush();
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
