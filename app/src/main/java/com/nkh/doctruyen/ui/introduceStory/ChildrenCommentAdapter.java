package com.nkh.doctruyen.ui.introduceStory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nkh.doctruyen.R;
import com.nkh.doctruyen.models.comment.ChildrenComment;
import com.nkh.doctruyen.models.comment.Comment;

import java.util.List;

public class ChildrenCommentAdapter extends RecyclerView.Adapter<ChildrenCommentAdapter.ChildrenCommentViewHolder> {

    Context context;
    List<ChildrenComment> mList;
    ItemClickListener clickListener;

    public ChildrenCommentAdapter(Context context, List<ChildrenComment> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ChildrenCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_children,parent,false);
        return new ChildrenCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenCommentViewHolder holder, int position) {
        ChildrenComment childrenComment = mList.get(position);
        holder.userComment.setText(childrenComment.getUserid() + " đã trả lời:");
        holder.contentComment.setText(childrenComment.getNoidung());
//        holder.replyChildren.setText(childrenComment.getTraloibinhluan());
    }

    @Override
    public int getItemCount() {
        Log.e("CommentAdapter", "comment: " + mList.size() );
        return mList.size();
    }

    public class ChildrenCommentViewHolder extends RecyclerView.ViewHolder{
        TextView userComment,contentComment,reply,replyChildren;
        public ChildrenCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userComment = itemView.findViewById(R.id.userComment);
            contentComment = itemView.findViewById(R.id.content_Comment);
            reply = itemView.findViewById(R.id.replyComment);
            replyChildren = itemView.findViewById(R.id.replyChildren);

            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickReply(mList.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface ItemClickListener {
        void onClickReply(ChildrenComment childrenComment);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
