package com.hzokbe.eigakan.model.user.request;

public class UserRequest {
    private String username;

    private String rawPassword;

    public UserRequest() {
    }

    public UserRequest(String username, String rawPassword) {
        this.username = username;

        this.rawPassword = rawPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
