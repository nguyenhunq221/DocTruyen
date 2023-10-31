package com.nkh.doctruyen.models.story;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoryModel {
    @SerializedName("listtruyen")
    private List<Story> danhsach;

    public List<Story> getDanhsach() {
        return danhsach;
    }

    public void setDanhsach(List<Story> danhsach) {
        this.danhsach = danhsach;
    }
}
