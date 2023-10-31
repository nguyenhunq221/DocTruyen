package com.nkh.doctruyen.ui.help;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.nkh.doctruyen.R;
import com.nkh.doctruyen.databinding.ActivityReviewAppBinding;

public class ReviewAppActivity extends AppCompatActivity {
    private ActivityReviewAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.topAppBarMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}