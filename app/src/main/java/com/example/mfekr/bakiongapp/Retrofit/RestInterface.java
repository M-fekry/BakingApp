package com.example.mfekr.bakiongapp.Retrofit;

import com.example.mfekr.bakiongapp.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {

    @GET("59121517_baking/baking.json")
    Call<List<Recipe>> getListRecipe();
}
