package com.nkh.doctruyen.ui.introduceStory;

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
import com.nkh.doctruyen.databinding.FragmentListChapterBinding;
import com.nkh.doctruyen.models.listChapter.Chapter;
import com.nkh.doctruyen.models.story.Story;

import java.util.List;

public class ListChapterFragment extends Fragment {

    private FragmentListChapterBinding binding;
    ListChapterAdapter adapter;
    private ListChapterViewmodel viewModel;
    PreferenceManager preferenceManager;
    private static int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ListChapterViewmodel.class);
        binding = FragmentListChapterBinding.inflate(getLayoutInflater());
        preferenceManager = new PreferenceManager(requireActivity());
        Bundle bundle = getArguments();
        if (bundle != null) {
            Story story = (Story) getArguments().getSerializable("key");

            id = Integer.parseInt(story.getId());

        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.listChapter.observe(getViewLifecycleOwner(), new Observer<List<Chapter>>() {
            @Override
            public void onChanged(List<Chapter> chapters) {
                showListChapter(chapters);
            }
        });
        String token = preferenceManager.getString(Constant.PRE.saveToken);
        viewModel.showChapter(token,id);

    }

    private void showListChapter(List<Chapter> mlist){
        adapter = new ListChapterAdapter(mlist,requireActivity());
        binding.rcvChapter.setAdapter(adapter);
    }
}