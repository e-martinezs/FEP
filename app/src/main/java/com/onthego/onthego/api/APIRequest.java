package com.onthego.onthego.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onthego.onthego.api.deserializer.FoodDeserializer;
import com.onthego.onthego.api.deserializer.PlaceDeserializer;
import com.onthego.onthego.api.deserializer.TokenDeserializer;
import com.onthego.onthego.database.viewmodels.FoodViewModel;
import com.onthego.onthego.database.viewmodels.PlaceViewModel;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Place;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRequest {

    private final String TOKEN = "TOKEN";
    private OnTheGoAPI api;
    private Context context;
    private View loadingLayout;
    private FoodViewModel foodViewModel;
    private PlaceViewModel placeViewModel;
    private SharedPreferences sharedPreferences;
    private boolean loading = false;
    private boolean logged = false;
    private String token;

    public APIRequest(Context context, View loadingLayout, FoodViewModel foodViewModel, PlaceViewModel placeViewModel){
        this.context = context;
        this.loadingLayout = loadingLayout;
        this.foodViewModel = foodViewModel;
        this.placeViewModel = placeViewModel;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        checkLogged();
    }

    public void checkLogged(){
        if (sharedPreferences.contains(TOKEN)){
            setLogged(true);
            token = sharedPreferences.getString(TOKEN, null);
        }else{
            setLogged(false);
            token = "";
        }
    }

    private void createAPIClient(Gson gson){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(OnTheGoAPI.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        api = retrofit.create(OnTheGoAPI.class);
    }

    public void login(String user, String password){
        setLoading(true);
        //Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
        Gson gson = new Gson();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(OnTheGoAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        OnTheGoAPI api = retrofit.create(OnTheGoAPI.class);

        String auth = "3";
        Call<Void> login = api.login(user, password, auth);
        login.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                System.out.println("TOKEN "+response.toString());
                System.out.println("TOKEN "+response.body());
                setLoading(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("LOGIN FAILED "+t.toString());
                setLoading(false);
            }
        });
    }

    public void signUp(String user, String password){
        setLoading(true);
        //Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
        Gson gson = new Gson();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(OnTheGoAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        OnTheGoAPI api = retrofit.create(OnTheGoAPI.class);

        Call<Void> signup = api.signUp(user, password, "3");
        signup.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                System.out.println("SIGNUP "+response.toString());
                setLoading(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("SIGNUP FAILED "+t.toString());
                setLoading(false);
            }
        });
    }

    public void getPlaces(){
        setLoading(true);
        Gson gson = new GsonBuilder().registerTypeAdapter(Place.class, new PlaceDeserializer()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(OnTheGoAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        api = retrofit.create(OnTheGoAPI.class);

        Call<List<Place>> places = api.getPlaces();
        places.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, retrofit2.Response<List<Place>> response) {
                /*System.out.println("Places "+response.body());
                for (Place p:response.body()){
                    System.out.println(p.getName());
                }*/
                List<Place> places = response.body();
                if (places != null){
                    placeViewModel.insertPlaces(places);
                }
                getFood();
                setLoading(false);
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                System.out.println("GET PLACES FAILED "+t.toString());
                setLoading(false);
            }
        });
    }

    public void getFood(){
        setLoading(true);
        Gson gson = new GsonBuilder().registerTypeAdapter(Food.class, new FoodDeserializer()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(OnTheGoAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        api = retrofit.create(OnTheGoAPI.class);

        Call<List<Food>> foods = api.getFoods();
        foods.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, retrofit2.Response<List<Food>> response) {
                /*System.out.println("Foods "+response.body());
                for (Food f:response.body()){
                    System.out.println(f.getName());
                }*/
                List<Food> foods = response.body();
                if (foods != null){
                    foodViewModel.insertFoods(foods);
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                System.out.println("GET FOODS FAILED "+t.toString());
                setLoading(false);
            }
        });
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        if (loading){
            loadingLayout.setVisibility(View.VISIBLE);
        }else{
            loadingLayout.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void refresh(){
        getPlaces();
    }
}
