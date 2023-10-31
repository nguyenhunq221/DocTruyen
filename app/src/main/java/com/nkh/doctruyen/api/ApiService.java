package com.nkh.doctruyen.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkh.doctruyen.config.Config;
import com.nkh.doctruyen.models.Login.LoginModel;
import com.nkh.doctruyen.models.addComment.AddCommentModel;
import com.nkh.doctruyen.models.checkFollow.CheckFollowModel;
import com.nkh.doctruyen.models.clickfollow.ClickFollowModel;
import com.nkh.doctruyen.models.comment.CommentModel;
import com.nkh.doctruyen.models.content.ContentModel;
import com.nkh.doctruyen.models.listChapter.ListChapterModel;
import com.nkh.doctruyen.models.regist.RegistModel;
import com.nkh.doctruyen.models.story.StoryModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }


    Gson gson = new GsonBuilder()
            .create();
    String baseUrl = Config.BASE_URL;
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(ApiService.getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST(ApiPath.REGISTER)
    Call<RegistModel> regist(@Query("username") String username,
                             @Query("password") String password,
                             @Query("email") String email
    );

    @POST(ApiPath.LOGIN)
    Call<LoginModel> login(@Query("username") String username,
                           @Query("password") String password
    );

    @GET(ApiPath.STORY)
    Call<StoryModel> Story(@Query("theloai") int theLoai
    );

    @GET(ApiPath.CHAPTER_CONTENT)
    Call<ContentModel> getContent(@Query("idchuong") int idchuong
    );

    @GET(ApiPath.LIST_CHAPTER)
    Call<ListChapterModel> getListChapter(@Query("idtruyen") int idtruyen
    );

    @GET(ApiPath.SEARCH)
    Call<StoryModel> search(@Query("text") String text
    );

    @GET(ApiPath.FOLLOW)
    Call<StoryModel> getListFollow(@Query("username") String username
    );

    @GET(ApiPath.CHECK_FOLLOW)
    Call<CheckFollowModel> checkFollow(@Query("tt") int tt,
                                       @Query("id_user") int idUser,
                                       @Query("id_truyen") int idTruyen
    );

    @GET(ApiPath.CHECK_FOLLOW)
    Call<ClickFollowModel> clickFollow(@Query("tt") int tt,
                                       @Query("id_user") int idUser,
                                       @Query("id_truyen") int idTruyen
    );

    @GET(ApiPath.COMMENT)
    Call<CommentModel> getListComment(@Query("idtruyen") int idtruyen
    );

    @GET(ApiPath.ADD_COMMENT)
    Call<AddCommentModel> addComment(@Query("id_user") int id_user,
                                     @Query("id_truyen") int id_truyen,
                                     @Query("noidung") String noidung,
                                     @Query("id_cha") String id_cha
    );
}
