package com.hzokbe.eigakan.model.user.request;

public class UserRequest {
    private String username;

    private String email;

    private String rawPassword;

    public UserRequest() {
    }

    public UserRequest(String username, String email, String rawPassword) {
        this.username = username;

        this.email = email;

        this.rawPassword = rawPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
