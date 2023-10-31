package com.nkh.doctruyen.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationBarView;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.Utils.CheckOnline;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.ActivityMainBinding;
import com.nkh.doctruyen.ui.allStory.StoryFragment;
import com.nkh.doctruyen.ui.favorite.FavorFragment;
import com.nkh.doctruyen.ui.home.HomeFragment;
import com.nkh.doctruyen.ui.info.InfoFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean doubleBackToExitPressedOnce = false;
    private int currentItemId = -1; // Add this line to keep track of the
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());

        if (!CheckOnline.isActive(this)){
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.wifi_check))
                    .setNegativeButton("Ok",null)
                    .show();
        }

        String username = preferenceManager.getString(Constant.PRE.saveUserName);
        Log.e("username", "username: "+username );

        setUpView();
    }

    //test

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.exit), Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void setUpView() {

        replaceFragment(new HomeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == currentItemId){
                    return false;
                }
                currentItemId = item.getItemId();

                //can replace by add  if (item.getItemId() == R.id.nav_drawer_settings) {} to remove warning

                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        return true;
                    case R.id.favorite:
                        replaceFragment(new FavorFragment());
                        return true;
                    case R.id.story:
                        replaceFragment(new StoryFragment());
                        return true;
                    case R.id.info:
                        replaceFragment(new InfoFragment());
                        return true;
                }
                return false;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    //test
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