package com.nkh.doctruyen.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.ActivitySplashBinding;
import com.nkh.doctruyen.ui.MainActivity;
import com.nkh.doctruyen.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        getSupportActionBar().hide();

        boolean hasLogIn = preferenceManager.getBoolean(Constant.PRE.saveLogin);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (hasLogIn) {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }

            }
        }, 2200);

    }
}