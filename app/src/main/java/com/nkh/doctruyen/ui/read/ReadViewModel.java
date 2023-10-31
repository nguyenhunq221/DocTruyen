package com.nkh.doctruyen.ui.read;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.content.ContentModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadViewModel extends AndroidViewModel {

    MutableLiveData<String> content = new MutableLiveData<>();
    MutableLiveData<ContentModel> contentModel = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public ReadViewModel(@NonNull Application application) {
        super(application);
    }

    public void showContent(int id){
        ApiService.apiService.getContent(id).enqueue(new Callback<ContentModel>() {
            @Override
            public void onResponse(Call<ContentModel> call, Response<ContentModel> response) {
                content.setValue(response.body().getNoidung());
                contentModel.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ContentModel> call, Throwable t) {
                Log.e("hung", "fail: "+t.getMessage());
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
