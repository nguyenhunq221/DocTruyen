package com.nkh.doctruyen.models.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentModel {
    @SerializedName("listtruyen")
    private List<Comment> listComment;

    public List<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(List<Comment> listComment) {
        this.listComment = listComment;
    }
}
