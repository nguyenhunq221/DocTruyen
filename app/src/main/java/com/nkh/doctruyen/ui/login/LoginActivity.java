package com.nkh.doctruyen.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.databinding.ActivityLoginBinding;
import com.nkh.doctruyen.ui.MainActivity;
import com.nkh.doctruyen.ui.regist.RegistActivity;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.success.observe(LoginActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                toHome();
                saveUser();
            }
        });

        viewModel.errorMessage.observe(LoginActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.error.observe(LoginActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, R.string.api_error, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.username.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                saveUserName(s);
            }
        });

        viewModel.userId.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                saveIdUser(s);
            }
        });

        viewModel.token.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                saveToken(s);
            }
        });

        setUpView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    private void setUpView() {
        binding.textResist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.userName.getText().toString();
                String password = binding.password.getText().toString();

                viewModel.login(username,password);
            }
        });
    }

    public void toHome(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
    }


    public void saveUser(){
        preferenceManager.putBoolean(Constant.PRE.saveLogin,true);
    }

    public void saveUserName(String userName){
       preferenceManager.putString(Constant.PRE.saveUserName,userName);
    }
    public void saveIdUser(String userId){
        preferenceManager.putString(Constant.PRE.saveUserId,userId);
    }

    public void saveToken(String token){
        preferenceManager.putString(Constant.PRE.saveToken,token);
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