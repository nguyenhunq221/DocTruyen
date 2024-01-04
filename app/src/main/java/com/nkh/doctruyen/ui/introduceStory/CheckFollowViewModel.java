package com.nkh.doctruyen.ui.introduceStory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.models.checkFollow.CheckFollowModel;
import com.nkh.doctruyen.models.clickfollow.ClickFollowModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckFollowViewModel extends AndroidViewModel {

    MutableLiveData<String> clickMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> followed = new MutableLiveData<>();
    MutableLiveData<Boolean> notFollow = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public CheckFollowViewModel(@NonNull Application application) {
        super(application);
    }

    public void checkFollow(String token,int userId ,int idTruyen){
        ApiService.apiService.checkFollow("Bearer "+ token,1,userId,idTruyen).enqueue(new Callback<CheckFollowModel>() {
            @Override
            public void onResponse(Call<CheckFollowModel> call, Response<CheckFollowModel> response) {
                if (response.body().getSuccess()==true){
                    followed.setValue(true);

                }else if (response.body().getSuccess() == false){
                    notFollow.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<CheckFollowModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    public void clickFollow(String token,int userId ,int idTruyen){
        ApiService.apiService.clickFollow("Bearer "+ token,2,userId,idTruyen).enqueue(new Callback<ClickFollowModel>() {
            @Override
            public void onResponse(Call<ClickFollowModel> call, Response<ClickFollowModel> response) {
                clickMessage.setValue(response.body().getSuccess());
            }

            @Override
            public void onFailure(Call<ClickFollowModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
