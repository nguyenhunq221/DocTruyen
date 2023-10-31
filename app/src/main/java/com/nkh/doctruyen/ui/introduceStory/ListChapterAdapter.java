package com.nkh.doctruyen.ui.introduceStory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nkh.doctruyen.databinding.ItemChapterBinding;
import com.nkh.doctruyen.models.listChapter.Chapter;
import com.nkh.doctruyen.ui.read.ReadActivity;
import java.util.ArrayList;
import java.util.List;

public class ListChapterAdapter extends RecyclerView.Adapter<ListChapterAdapter.ListChapterViewHolder> {

    List<Chapter> mList;
    Context context;
    List<String> listId = new ArrayList<>();

    public ListChapterAdapter(List<Chapter> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChapterBinding binding = ItemChapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListChapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListChapterViewHolder holder, int position) {
        Chapter chapter = mList.get(position);
        holder.binding.nameChapter.setText(chapter.getTieudechuong());
        holder.binding.numberChapter.setText("Chương " + chapter.getChuong());
        int total = mList.size();

        for (int i = 0; i < mList.size(); i++) {
            listId.add(mList.get(i).getId());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReadActivity.class);
                intent.putExtra("id", chapter.getId());
                intent.putStringArrayListExtra("myList", (ArrayList<String>) listId);
                intent.putExtra("total", total);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ListChapterViewHolder extends RecyclerView.ViewHolder {

        private ItemChapterBinding binding;

        public ListChapterViewHolder(@NonNull ItemChapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
