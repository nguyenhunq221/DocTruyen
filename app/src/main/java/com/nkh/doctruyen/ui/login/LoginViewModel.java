package com.nkh.doctruyen.ui.login;

import android.app.Application;
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

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String userName,String password){
        ApiService.apiService.login(userName,password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body().getSuccess()==true){
                    success.setValue(true);
                    username.setValue(response.body().getUsername());
                    userId.setValue(response.body().getUserId());
                }else if (response.body().getSuccess() == false){
                    errorMessage.setValue("Login fail");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                error.setValue(t.getMessage());
            }
        });
    }
}
