package com.nkh.doctruyen.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.FragmentHomeBinding;
import com.nkh.doctruyen.models.story.Story;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class HomeFragment extends Fragment  {
    private FragmentHomeBinding binding;
    HotStoryAdapter adapter;
    private HomeViewModel viewModel;

    PreferenceManager preferenceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        preferenceManager = new PreferenceManager(requireActivity());
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.listStory.observe(this.getActivity(), new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                showStory(stories);
            }
        });

        viewModel.listTextStory.observe(this.getActivity(), new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                showTienHiepStory(stories);
            }
        });
        viewModel.errorMessage.observe(this.getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.listSlideStory.observe(this.getViewLifecycleOwner(), new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                showSlideStory(stories);
            }
        });

        String token = preferenceManager.getString(Constant.PRE.saveToken);

        viewModel.showStory(token);
        viewModel.showTienHiepStory(token);
        viewModel.showSlideStory(token);

    }

    private void showStory(List<Story> mlist){
        adapter = new HotStoryAdapter(mlist,requireActivity());
        binding.rcvHotStory.setAdapter(adapter);
    }
    private void showTienHiepStory(List<Story> mlist){
        adapter = new HotStoryAdapter(mlist,requireActivity());
        binding.rcvTextStory.setAdapter(adapter);
    }

    private void showSlideStory(List<Story> mlist){
        SliderAdapter adapter = new SliderAdapter(this.getActivity(), mlist);
        //below method is used to set auto cycle direction in left to right direction you can change according to requirement.
        binding.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        //below method is used to setadapter to sliderview.
        binding.slider.setSliderAdapter(adapter);
        //below method is use to set scroll time in seconds.
        binding.slider.setScrollTimeInSec(3);
        //to set it scrollable automatically we use below method.
        binding.slider.setAutoCycle(true);
        //to start autocycle below method is used.
        binding.slider.startAutoCycle();
    }

}