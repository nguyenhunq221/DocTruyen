package com.nkh.doctruyen.models.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {
    @SerializedName("idtr")
    private String id;
    @SerializedName("user_id")
    private String userid;
    @SerializedName("truyen_id")
    private String truyenid;
    private String noidung;
    private List<ChildrenComment> traloibinhluan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTruyenid() {
        return truyenid;
    }

    public void setTruyenid(String truyenid) {
        this.truyenid = truyenid;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public List<ChildrenComment> getTraloibinhluan() {
        return traloibinhluan;
    }

    public void setTraloibinhluan(List<ChildrenComment> traloibinhluan) {
        this.traloibinhluan = traloibinhluan;
    }
}
