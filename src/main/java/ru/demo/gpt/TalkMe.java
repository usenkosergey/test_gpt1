package ru.demo.gpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Usenko Sergey, 06.07.2024
 */
public class TalkMe {
    public static void main(String[] args) {
        try {
            talkMe();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void talkMe() throws MalformedURLException {

        try {

//            String request = "{\"dateRange\": { " +
//                    "    \"start\": \"2024-07-01\", " +
//                    "    \"stop\": \"2024-07-08\" " +
//                    "  }, " +
//                    "  \"client\": { " +
//                    "    \"searchId\": 9, " +
//                    "    \"clientId\": \"\"" +
//                    "  }, " +
//                    "  \"operator\": { " +
//                    "    \"login\": \"q1122333@yandex.ru\" " +
//                    "  }, " +
//                    "  \"dialogClosed\": true, " +
//                    "  \"haveNoAnswer\": true, " +
//                    "  \"sender\": \"operator\", " +
//                    "  \"status\": \"undelivered\", " +
//                    "  \"skipEmailText\": false" +
//                    "}";

//            String request = "{\"dateRange\": { " +
//                    "    \"start\": \"2024-07-01\", " +
//                    "    \"stop\": \"2024-07-08\" " +
//                    "  }, " +
//                    "  \"client\": { " +
//                    "    \"searchId\": 9 " +
//                    "  }, " +
//                    "  \"operator\": { " +
//                    "    \"login\": \"q1122333@yandex.ru\" " +
//                    "  }, " +
//                    "  \"sender\": \"operator\", " +
//                    "  \"status\": \"undelivered\", " +
//                    "  \"skipEmailText\": false" +
//                    "}";

            String request = "{\"page\": 0, " +
                    "\"limit\": 30, " +
                    "\"lastActivityDateTimeRange\": { " +
                    "\"start\": \"2024-07-01\", " +
                    "\"stop\": \"2024-07-08\" " +
                    "}, " +
                    "\"isOnline\": false}";
//            URL url = new URL("https://lcab.talk-me.ru/json/v1.0/chat/message/getList");
            URL url = new URL("https://lcab.talk-me.ru/json/v1.0/chat/client/getList");
//            URL url = new URL("https://lcab.talk-me.ru/json/v1.0/cabinet/contact/getGroups");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Content-Type", "application/json");
            conn.addRequestProperty("X-Token", "8ni4mvsi32wmcyyc3nuz69bbl2gpd4md9xhwrecqkankbl7j1gqq4kh8mmfrac1m");
//            conn.addRequestProperty("X-Token", "vxy8zk8iq9w7mb5ea587y1zvh8bcn5de70v1x0hlgofn3ni47ok1ojy4j85l3gmd");
            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//            writer.write(request);
            writer.write(request);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
