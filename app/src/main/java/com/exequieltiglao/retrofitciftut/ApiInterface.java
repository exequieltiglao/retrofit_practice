package com.exequieltiglao.retrofitciftut;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("photos")
    Call<List<Photos>> getPhotos();

    @GET("photos/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int photoId);

}
