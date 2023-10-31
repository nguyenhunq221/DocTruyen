package com.nkh.doctruyen.models.story;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Story implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("tentruyen")
    private String tentruyen;
    @SerializedName("tomtatnd")
    private String tomtatnd;
    @SerializedName("chuongmoi")
    private int chuongmoi;
    @SerializedName("theloai")
    private String theloai;
    @SerializedName("danhgia")
    private double danhgia;
    @SerializedName("image")
    private String image;
    @SerializedName("trangthai")
    private String trangthai;
    @SerializedName("ngaytao")
    private String ngaytao;

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getTomtatnd() {
        return tomtatnd;
    }

    public void setTomtatnd(String tomtatnd) {
        this.tomtatnd = tomtatnd;
    }

    public int getChuongmoi() {
        return chuongmoi;
    }

    public void setChuongmoi(int chuongmoi) {
        this.chuongmoi = chuongmoi;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public double getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(double danhgia) {
        this.danhgia = danhgia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }
}

