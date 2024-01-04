package com.nkh.doctruyen.ui.read;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.snackbar.Snackbar;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.ActivityReadBinding;
import com.nkh.doctruyen.models.content.ContentModel;

import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private ActivityReadBinding binding;
    private ReadViewModel viewModel;
    private static int position ;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(this);
        viewModel = new ViewModelProvider(this).get(ReadViewModel.class);
        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        int total = intent.getIntExtra("total", 0);
        position = intent.getIntExtra("position", 0);

        List<String> myList = intent.getStringArrayListExtra("myList");

        String token = preferenceManager.getString(Constant.PRE.saveToken);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position ++;
                if ( position < total){
                    viewModel.showContent( token,Integer.parseInt(myList.get(position)));
                }else {
                    Snackbar.make(binding.getRoot(),R.string.no_more_chapter,Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0 && position <= total ){
                    position --;
                    viewModel.showContent( token,Integer.parseInt(myList.get(position)));
                }
            }
        });
        viewModel.contentModel.observe(this, new Observer<ContentModel>() {
            @Override
            public void onChanged(ContentModel contentModel) {
                loadContent(contentModel);
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        viewModel.showContent(token,id);
    }

    public void loadContent(ContentModel contentModel) {
        binding.myWebView.getSettings().setJavaScriptEnabled(true);
        binding.myWebView.loadData(contentModel.getNoidung(), "text/html", null);
        WebSettings webSettings = binding.myWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

        binding.nameChapter.setText(contentModel.getTieudechuong());
        binding.numberChapter.setText( getString(R.string.chapter)+ contentModel.getChuong());

    }
}