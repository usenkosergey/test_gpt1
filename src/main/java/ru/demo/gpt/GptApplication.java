package ru.demo.gpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GptApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GptApplication.class, args);
        ApplicationContext context = SpringApplication.run(GptApplication.class, args);
        OpenAi bean = (OpenAi) context.getBean("openAi");
        bean.chatGPT();
    }

}
