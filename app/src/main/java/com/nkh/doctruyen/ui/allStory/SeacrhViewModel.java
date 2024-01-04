package com.nkh.doctruyen.ui.allStory;

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

public class SeacrhViewModel extends AndroidViewModel {
    MutableLiveData<List<Story>> listSearchStory = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public SeacrhViewModel(@NonNull Application application) {
        super(application);
    }

    public void showStory(String token,String textSearch){
        ApiService.apiService.search("Bearer "+ token,textSearch).enqueue(new Callback<StoryModel>() {
            @Override
            public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                listSearchStory.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<StoryModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
