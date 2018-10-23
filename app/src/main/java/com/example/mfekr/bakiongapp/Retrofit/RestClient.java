package com.example.mfekr.bakiongapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/";

    public static Retrofit getApiClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
