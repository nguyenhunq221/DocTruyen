package com.nkh.doctruyen.models.listChapter;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String id;
    private String chuong;
    private String tieudechuong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
