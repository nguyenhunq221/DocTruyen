package com.nkh.doctruyen.ui.regist;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.regist.RegistModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistViewmodel extends AndroidViewModel {

    MutableLiveData<String> message = new MutableLiveData<>();
    MutableLiveData<Boolean> success = new MutableLiveData<>();
    MutableLiveData<String> id = new MutableLiveData<>();
    MutableLiveData<String> user = new MutableLiveData<>();

    public RegistViewmodel(@NonNull Application application) {
        super(application);
    }

    public void Regist(String userName,String password,String email){
        ApiService.apiService.regist(userName,password,email).enqueue(new Callback<RegistModel>() {
            @Override
            public void onResponse(Call<RegistModel> call, Response<RegistModel> response) {
                if (response.body().getSuccess() == true){
                    success.setValue(true);
                    id.setValue(response.body().getId());
                    user.setValue(response.body().getUsername());
                    message.setValue(response.body().getMessage());
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
