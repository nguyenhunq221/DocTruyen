package com.nkh.doctruyen.ui.introduceStory;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.models.listChapter.Chapter;
import com.nkh.doctruyen.models.listChapter.ListChapterModel;
import java.util.List;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChapterViewmodel extends AndroidViewModel {

    MutableLiveData<List<Chapter>> listChapter = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public ListChapterViewmodel(@NonNull Application application) {
        super(application);
    }

    public void showChapter(int id){
        ApiService.apiService.getListChapter(id).enqueue(new Callback<ListChapterModel>() {
            @Override
            public void onResponse(Call<ListChapterModel> call, Response<ListChapterModel> response) {
                listChapter.setValue(response.body().getDanhsach());
            }

            @Override
            public void onFailure(Call<ListChapterModel> call, Throwable t) {
                if (t instanceof TimeoutException){
                    errorMessage.setValue("Time Out");
                }else {
                    errorMessage.setValue(t.getMessage());
                }

            }
        });
    }
}
