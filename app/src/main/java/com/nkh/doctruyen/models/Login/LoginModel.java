package com.nkh.doctruyen.models.Login;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    private Boolean success;
    private String username;
    @SerializedName("id")
    private String userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
