package com.nkh.doctruyen.models.content;

public class ContentModel {
    private String noidung;
    private String chuong;
    private String tieudechuong;

    public String getChuong() {
        return chuong;
    }

    public void setChuong(String chuong) {
        this.chuong = chuong;
    }

    public String getTieudechuong() {
        return tieudechuong;
    }

    public void setTieudechuong(String tieudechuong) {
        this.tieudechuong = tieudechuong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
