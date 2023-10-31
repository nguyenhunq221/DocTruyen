package com.nkh.doctruyen.ui.allStory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.ActivitySearchStoryBinding;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.ui.home.HotStoryAdapter;

import java.util.List;

public class SearchStoryActivity extends AppCompatActivity {
    private ActivitySearchStoryBinding binding;
    private SeacrhViewModel viewModel;
    HotStoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(SearchStoryActivity.this).get(SeacrhViewModel.class);
        viewModel.listSearchStory.observe(SearchStoryActivity.this, new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                if (stories.isEmpty()){
                    binding.noResult1.setVisibility(View.VISIBLE);
                    binding.noResult.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                }else {
                    adapter = new HotStoryAdapter(stories,SearchStoryActivity.this);
                    binding.rcvSearchStory.setAdapter(adapter);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        });

        Intent intent = getIntent();
        String textSearch = intent.getStringExtra(Constant.INTENT.INTENT_SEARCH);
        Log.e("hung1", "text1: "+textSearch );
        viewModel.showStory(textSearch);

        viewModel.errorMessage.observe(SearchStoryActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(SearchStoryActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        setUpView();

    }

    private void setUpView(){
        binding.topAppBarMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.swiperefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Intent intent = getIntent();
                        String textSearch = intent.getStringExtra(Constant.INTENT.INTENT_SEARCH);
                        Log.e("hung1", "text1: "+textSearch );
                        viewModel.showStory(textSearch);
                        binding.swiperefresh.setRefreshing(false);
                    }
                }
        );

        Intent intent = getIntent();
        String textSearch = intent.getStringExtra(Constant.INTENT.INTENT_SEARCH);
        binding.topAppBarMenu.setTitle(textSearch);
    }
}