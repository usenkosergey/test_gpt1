package ru.demo.gpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class GptApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(GptApplication.class, args);
        ApplicationContext context = SpringApplication.run(GptApplication.class, args);
//        OpenAi bean = (OpenAi) context.getBean("openAi");
//        bean.chatGPT();

//        RetrieveBatchService bean = context.getBean(RetrieveBatchService.class);
////        bean.sendFile();
//        bean.retrieveBatch();

        UploadService bean = context.getBean(UploadService.class);
        bean.uploadFile();

    }

}
