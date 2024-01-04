package com.nkh.doctruyen.ui.introduceStory;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nkh.doctruyen.Utils.PreferenceManager;
import com.nkh.doctruyen.api.ApiService;
import com.nkh.doctruyen.config.Constant;
import com.nkh.doctruyen.models.addComment.AddCommentModel;
import com.nkh.doctruyen.models.comment.Comment;
import com.nkh.doctruyen.models.comment.CommentModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends AndroidViewModel {
    public CommentViewModel(@NonNull Application application) {
        super(application);
    }
    MutableLiveData<List<Comment>> listComment = new MutableLiveData<>();
    MutableLiveData<Boolean> success = new MutableLiveData<>();
    MutableLiveData<Boolean> fail = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void showComment(String token,int id){
        ApiService.apiService.getListComment("Bearer "+ token,id).enqueue(new Callback<CommentModel>() {
            @Override
            public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {
                listComment.setValue(response.body().getListComment());
            }

            @Override
            public void onFailure(Call<CommentModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    public void addComment(String token,int id,int idTruyen,String noidung,String idCha){
        ApiService.apiService.addComment("Bearer "+ token,id,idTruyen,noidung,idCha).enqueue(new Callback<AddCommentModel>() {
            @Override
            public void onResponse(Call<AddCommentModel> call, Response<AddCommentModel> response) {
                if (response.body().getSuccess() == true){
                    success.setValue(true);
                }else {
                    fail.setValue(false);
                }

            }

            @Override
            public void onFailure(Call<AddCommentModel> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}
