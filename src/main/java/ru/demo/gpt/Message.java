package ru.demo.gpt;

/**
 * @author Usenko Sergey, 13.07.2024
 */
public class Message {
    String role;
    String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
