package com.nkh.doctruyen.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.Login.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    MutableLiveData<Boolean> success = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    MutableLiveData<String> error = new MutableLiveData<>();
    MutableLiveData<String> username = new MutableLiveData<>();
    MutableLiveData<String> userId = new MutableLiveData<>();
    MutableLiveData<String> token = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String userName,String password){
        ApiService.apiService.login(userName,password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body().isStatus() == true){
                    success.setValue(true);
                    username.setValue(response.body().getData().getUser().getName());
                    userId.setValue(response.body().getData().getUser().getId());
                    token.setValue(response.body().getData().getToken());
                }else {
                    errorMessage.setValue(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                error.setValue(t.getMessage());
            }
        });
    }
}
