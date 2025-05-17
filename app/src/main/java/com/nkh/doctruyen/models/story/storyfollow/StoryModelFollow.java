package com.nkh.doctruyen.models.story.storyfollow;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class StoryModelFollow {
    @SerializedName("listtruyen")
    private List<StoryFollow> danhsach;

    public List<StoryFollow> getDanhsach() {
        return danhsach;
    }

    public void setDanhsach(List<StoryFollow> danhsach) {
        this.danhsach = danhsach;
    }
}
