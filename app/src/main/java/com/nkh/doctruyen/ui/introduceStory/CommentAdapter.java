package com.nkh.doctruyen.ui.introduceStory;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.nkh.doctruyen.R;
import com.nkh.doctruyen.models.comment.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Context context;
    List<Comment> mList;
    ItemClickListener clickListener;
    ChildrenCommentAdapter childrenCommentAdapter;

    public CommentAdapter(Context context, List<Comment> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = mList.get(position);
        holder.userComment.setText(comment.getUserid()+":");
        holder.contentComment.setText(comment.getNoidung());
        childrenCommentAdapter = new ChildrenCommentAdapter(context,comment.getTraloibinhluan());
        holder.rcvChildrenComment.setAdapter(childrenCommentAdapter);
        childrenCommentAdapter.notifyDataSetChanged();

        if (comment.getTraloibinhluan().size() > 0){
            holder.imgLine.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        Log.e("CommentAdapter", "comment: " + mList.size() );
        return mList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView userComment,contentComment,reply;
        ImageView imgLine;
        RecyclerView rcvChildrenComment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userComment = itemView.findViewById(R.id.userComment);
            contentComment = itemView.findViewById(R.id.content_Comment);
            reply = itemView.findViewById(R.id.replyComment);
            rcvChildrenComment = itemView.findViewById(R.id.rcvReplyComment);
            imgLine = itemView.findViewById(R.id.img);

            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickReply(mList.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface ItemClickListener {
        void onClickReply(Comment comment);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
