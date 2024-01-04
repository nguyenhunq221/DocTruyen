package com.nkh.doctruyen.models.regist;

import com.google.gson.annotations.SerializedName;
import com.nkh.doctruyen.models.Login.User;

public class Data {
    @SerializedName("user")
    private User user;
    @SerializedName("token")
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
