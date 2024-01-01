package com.nkh.doctruyen.ui.regist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.nkh.doctruyen.R;
import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.databinding.ActivityRegistBinding;
import com.nkh.doctruyen.ui.MainActivity;
import com.nkh.doctruyen.ui.login.LoginActivity;

public class RegistActivity extends AppCompatActivity {
    private ActivityRegistBinding binding;
    RegistViewmodel viewModel;
    String userName,password,confirmPassword,email;
    PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegistViewmodel.class);
        preferenceManager = new PreferenceManager(RegistActivity.this);
        binding = ActivityRegistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel.success.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                toHome();
                saveUser();
            }
        });

        viewModel.id.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                saveIdUser(s);
            }
        });

        viewModel.user.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                saveUserName(s);
            }
        });

        viewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Snackbar.make(binding.getRoot(),s,Snackbar.LENGTH_SHORT).show();
            }
        });

        setUpView();
    }

    private void setUpView() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordValidator passwordValidator = new PasswordValidator();

                userName = binding.userName.getText().toString();
                password = binding.password.getText().toString();
                confirmPassword = binding.confirmPassword.getText().toString();
                email = binding.gmail.getText().toString();
                if (validate() && passwordValidator.validate(password)){
                    viewModel.Regist(userName,password,email);
                }else if (TextUtils.isEmpty(userName)){
                    Snackbar.make(binding.getRoot(),getString(R.string.empty_userName),Snackbar.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(email)){
                    Snackbar.make(binding.getRoot(),getString(R.string.empty_email),Snackbar.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)){
                    Snackbar.make(binding.getRoot(),getString(R.string.not_math_password),Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(binding.getRoot(),getString(R.string.not_accept_password),Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validate(){
        if (!TextUtils.isEmpty(userName)&& !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword) ){
            if (binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())){
                return true;
            }
        }
        return false;
    }

    public void toHome(){
        startActivity(new Intent(RegistActivity.this, MainActivity.class));
        Toast.makeText(RegistActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
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