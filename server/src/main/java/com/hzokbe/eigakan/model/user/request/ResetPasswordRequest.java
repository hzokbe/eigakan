package com.hzokbe.eigakan.model.user.request;

public class ResetPasswordRequest {
    private String rawPassword;

    public ResetPasswordRequest(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
