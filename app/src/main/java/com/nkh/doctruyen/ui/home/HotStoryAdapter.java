package com.nkh.doctruyen.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.nkh.doctruyen.config.Config;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.ui.introduceStory.IntroduceStoryActivity;
import java.util.List;

public class HotStoryAdapter extends RecyclerView.Adapter<HotStoryAdapter.HotStoryViewholder> {

    List<Story> mList;
    Context context;
    ItemClickListener listener;

    public HotStoryAdapter(List<Story> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public HotStoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story,parent,false);
        return new HotStoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotStoryViewholder holder, int position) {
        Story story = mList.get(position);

        holder.storyName.setText(story.getTentruyen());

        String urlImage = Config.URL_IMAGE + story.getImage();

        Glide.with(context)
                .load(urlImage)
                .centerCrop()
                .into(holder.imgStory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IntroduceStoryActivity.class);
                intent.putExtra("story",story);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HotStoryViewholder extends RecyclerView.ViewHolder {

        TextView storyName;
        ImageView imgStory;

        public HotStoryViewholder(@NonNull View itemView) {
            super(itemView);
            storyName = itemView.findViewById(R.id.storyName);
            imgStory = itemView.findViewById(R.id.imageStory);
        }
    }
    public interface ItemClickListener{
        void onClickStory(Story story);
    }
    public void setClickListener(ItemClickListener itemClickListener){
        this.listener = itemClickListener;
    }

}
