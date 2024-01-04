package com.nkh.doctruyen.ui.regist;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.regist.RegistModel;
import com.nkh.doctruyen.models.regist.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistViewmodel extends AndroidViewModel {

    MutableLiveData<String> message = new MutableLiveData<>();
    MutableLiveData<Boolean> success = new MutableLiveData<>();
    MutableLiveData<String> id = new MutableLiveData<>();
    MutableLiveData<String> token = new MutableLiveData<>();
    MutableLiveData<String> user = new MutableLiveData<>();

    public RegistViewmodel(@NonNull Application application) {
        super(application);
    }

    public void Regist(String userName,String password,String email){
        ApiService.apiService.regist(userName,password,email).enqueue(new Callback<RegistModel>() {
            @Override
            public void onResponse(Call<RegistModel> call, Response<RegistModel> response) {
                if (response.body().isStatus() == true){
                    success.setValue(true);
                    id.setValue(response.body().getData().getUser().getId());
                    user.setValue(response.body().getData().getUser().getName());
                    message.setValue(response.body().getMessage());
                    token.setValue(response.body().getData().getToken());
                }else {
                    message.setValue(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegistModel> call, Throwable t) {
                message.setValue(t.getMessage());
            }
        });
    }
}
