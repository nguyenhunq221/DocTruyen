package com.nkh.doctruyen.ui.introduceStory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Config;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.ActivityIntroduceStoryBinding;
import com.nkh.doctruyen.models.story.Story;

public class IntroduceStoryActivity extends AppCompatActivity {
    private ActivityIntroduceStoryBinding binding;
    private TabAdapter adapter;
    CheckFollowViewModel viewModel;
    private static String title = "";

    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroduceStoryBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(CheckFollowViewModel.class);
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(this);

        viewModel.followed.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.followImg.setImageResource(R.drawable.followed);
                title = "Bỏ yêu thích ";
            }
        });

        viewModel.notFollow.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.followImg.setImageResource(R.drawable.not_follow);
                title = "Yêu thích ";
            }
        });

        adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new ReviewStoryFragment(), getString(R.string.introduce_story));
        adapter.addFragment(new ListChapterFragment(), getString(R.string.introduce_listChapter));

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("story");
        Log.e("hung1", "date: " + story.getId());

        int userId = Integer.parseInt(preferenceManager.getString(Constant.PRE.saveUserId));
        int idTruyen = Integer.parseInt(story.getId());
        String token = preferenceManager.getString(Constant.PRE.saveToken);
        viewModel.checkFollow(token,userId, idTruyen);

        viewModel.clickMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Snackbar.make(binding.layoutIntroduce, s, Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.followImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(IntroduceStoryActivity.this)

                        .setTitle(title + story.getTentruyen() + " ?")
                        .setPositiveButton("Từ chối ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewModel.clickFollow(token,userId, idTruyen);
                                viewModel.checkFollow(token,userId,idTruyen);
                            }
                        })
                        .show();
            }
        });

        String urlImage = Config.URL_IMAGE + story.getImage();
        Glide.with(this)
                .load(urlImage)
                .centerCrop()
                .into(binding.imageStory);

        binding.storyName.setText(story.getTentruyen());
        binding.category.setText(story.getTheloai());
        binding.status.setText(story.getTrangthai());
        if (story.getNgaytao()!= null){

        }else {
            binding.date.setText("");
        }


        Bundle bundle = new Bundle();
        bundle.putSerializable("key", story);
        ReviewStoryFragment fragment = new ReviewStoryFragment();
        fragment.setArguments(bundle);

        ListChapterFragment listChapterFragment = new ListChapterFragment();
        listChapterFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.viewPager, fragment);
        fragmentTransaction.add(R.id.viewPager, listChapterFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        String token = preferenceManager.getString(Constant.PRE.saveToken);
        Intent intent = getIntent();
        Story story = (Story) intent.getSerializableExtra("story");
        int userId = Integer.parseInt(preferenceManager.getString(Constant.PRE.saveUserId));
        int idTruyen = Integer.parseInt(story.getId());
        viewModel.checkFollow(token,userId, idTruyen);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}