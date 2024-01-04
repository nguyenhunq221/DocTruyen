package com.nkh.doctruyen.ui.home;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.models.story.StoryModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    MutableLiveData<List<Story>> listStory = new MutableLiveData<>();
    MutableLiveData<List<Story>> listTextStory = new MutableLiveData<>();

    MutableLiveData<List<Story>> listSlideStory = new MutableLiveData<>();

    public MutableLiveData<List<Story>> listRecommend = new MutableLiveData<>();

    public MutableLiveData<List<Story>> listNovel = new MutableLiveData<>();

    MutableLiveData<String> errorMessage = new MutableLiveData<>();



    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void showStory(String token){
        ApiService.apiService.Story("Bearer "+ token,1).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                Log.e("hung88", "onResponse: "+ call.request() );
                 listStory.setValue(response.body().getDanhsach());
            }
            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
    public void showTienHiepStory(String token){
        ApiService.apiService.Story("Bearer "+ token,4).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                Log.e("hung88", "onResponse: "+ call.request());
                listTextStory.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    public void showSlideStory(String token){
        ApiService.apiService.Story("Bearer "+ token,3).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                listSlideStory.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                Log.e("hung", "fail: "+t.getMessage());
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    public void showRecommendStory(String token){
        ApiService.apiService.Story("Bearer "+ token,5).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                listRecommend.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    public void showNovelStory(String token){
        ApiService.apiService.Story("Bearer "+ token,2).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                listNovel.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
