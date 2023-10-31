package com.nkh.doctruyen.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.nkh.doctruyen.config.Config;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.ui.introduceStory.IntroduceStoryActivity;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    Context context;
    List<Story> images;

    public SliderAdapter(Context context, List<Story> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        Story story = images.get(position);
        String urlImage = Config.URL_IMAGE + story.getImage();

        Glide.with(context)
                .load(urlImage)
                .centerCrop()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IntroduceStoryActivity.class);
                intent.putExtra("story",story);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        return images.size();
    }

    static class SliderAdapterViewHolder extends ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageHome);
            this.itemView = itemView;
        }
    }
}
