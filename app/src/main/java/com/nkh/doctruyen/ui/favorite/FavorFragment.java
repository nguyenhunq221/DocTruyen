package com.nkh.doctruyen.ui.favorite;

import android.os.Bundle;
import android.util.Log;
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
import com.nkh.doctruyen.databinding.FragmentFavorBinding;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.ui.home.HotStoryAdapter;
import java.util.List;


public class FavorFragment extends Fragment {
    private FragmentFavorBinding binding;
    FavorViewModel viewModel;
    HotStoryAdapter adapter;
    PreferenceManager preferenceManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavorBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(FavorViewModel.class);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferenceManager = new PreferenceManager(requireActivity());
        viewModel.listFollowStory.observe(getViewLifecycleOwner(), new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                showListFollow(stories);
            }
        });

        String userName = preferenceManager.getString(Constant.PRE.saveUserName);
        Log.e("hung", "username: "+userName );
        viewModel.showStoryFollow(userName);
    }

    private void showListFollow(List<Story> stories) {
        if (stories.isEmpty()){
            binding.noResult1.setVisibility(View.VISIBLE);
            binding.noResult.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }else {
            adapter = new HotStoryAdapter(stories, getActivity());
            binding.rcvFollow.setAdapter(adapter);
            binding.progressBar.setVisibility(View.GONE);
        }
    }

}