package com.nkh.doctruyen.ui.allStory;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.story.storyfollow.StoryFollow;
import com.nkh.doctruyen.models.story.storyfollow.StoryModelFollow;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends AndroidViewModel {

    MutableLiveData<List<StoryFollow>> listSearchStory = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void showSearchStory(String token,String textSearch){
        ApiService.apiService.search("Bearer "+ token,textSearch).enqueue(new Callback<StoryModelFollow>() {
            @Override
            public void onResponse(Call<StoryModelFollow> call, Response<StoryModelFollow> response) {
                listSearchStory.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModelFollow> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
