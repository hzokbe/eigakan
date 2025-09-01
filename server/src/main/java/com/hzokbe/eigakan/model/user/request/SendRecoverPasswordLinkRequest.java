package com.hzokbe.eigakan.model.user.request;

public class SendRecoverPasswordLinkRequest {
    private String email;

    public SendRecoverPasswordLinkRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
