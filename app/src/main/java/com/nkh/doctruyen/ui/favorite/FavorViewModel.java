package com.nkh.doctruyen.ui.favorite;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.story.Story;
import com.nkh.doctruyen.models.story.StoryModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavorViewModel extends AndroidViewModel {

    MutableLiveData<List<Story>> listFollowStory = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public FavorViewModel(@NonNull Application application) {
        super(application);
    }
    public void showStoryFollow(String token,String username){
        ApiService.apiService.getListFollow("Bearer "+ token,username).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {

                listFollowStory.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
