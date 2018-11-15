package com.onthego.onthego.api;

import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OnTheGoAPI {
    String BASE_URL = "https://proyectofep2018.herokuapp.com";

    @GET("/app/users/2")
    Call<List<Place>> getPlaces();

    @GET("/api/tasks")
    Call<List<Food>> getFoods();

    @FormUrlEncoded
    @POST("/app/users/login")
    Call<Void> login(@Field("user") String user, @Field("password") String password, @Field("auth") String auth);

    @FormUrlEncoded
    @POST("/app/users")
    Call<Void> signUp(@Field("user") String user, @Field("password") String password, @Field("auth") String auth);
}
