package com.example.POSApi.model;

public class LoginResponse {
    private String status;
    private String message;
    private String token;
    private Long userId;  // eklendi

    public LoginResponse(String status, String message, String token, Long userId) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.userId = userId;
    }

    // Getter & Setter'lar
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
