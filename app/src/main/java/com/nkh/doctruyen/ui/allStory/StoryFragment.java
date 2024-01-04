package com.nkh.doctruyen.ui.allStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.FragmentStoryBinding;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.ui.home.HomeViewModel;
import com.nkh.doctruyen.ui.home.HotStoryAdapter;
import java.util.List;


public class StoryFragment extends Fragment {

    private FragmentStoryBinding binding;
    HotStoryAdapter adapter;
    private HomeViewModel viewModel;
    PreferenceManager preferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoryBinding.inflate(inflater, container, false);
        preferenceManager = new PreferenceManager(requireActivity());
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       viewModel.listRecommend.observe(getViewLifecycleOwner(), new Observer<List<Story>>() {
           @Override
           public void onChanged(List<Story> stories) {
               showRecommendStory(stories);
           }
       });

       viewModel.listNovel.observe(getViewLifecycleOwner(), new Observer<List<Story>>() {
           @Override
           public void onChanged(List<Story> stories) {
               showNovelStory(stories);
           }
       });

        String token = preferenceManager.getString(Constant.PRE.saveToken);
       viewModel.showRecommendStory(token);
       viewModel.showNovelStory(token);

       setUpView();

    }

    private void showRecommendStory(List<Story> mlist){
        adapter = new HotStoryAdapter(mlist,requireActivity());
        binding.rcvRecommend.setAdapter(adapter);
    }
    private void showNovelStory(List<Story> mlist){
        adapter = new HotStoryAdapter(mlist,requireActivity());
        binding.rcvNovel.setAdapter(adapter);
    }

    private void setUpView(){

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchStoryActivity.class);
                String textSearch = binding.searchView.getText().toString();
                intent.putExtra(Constant.INTENT.INTENT_SEARCH,textSearch);
                startActivity(intent);
            }
        });
    }
}