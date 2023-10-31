package com.nkh.doctruyen.models.regist;

import com.google.gson.annotations.SerializedName;

public class RegistModel {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("messeage")
    private String message;
    @SerializedName("username")
    private String username;
    @SerializedName("id")
    private String id;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
