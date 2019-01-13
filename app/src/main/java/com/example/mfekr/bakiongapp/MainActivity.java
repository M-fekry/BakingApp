package com.example.mfekr.bakiongapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mfekr.bakiongapp.Adapters.RecipeRecyclerViewAdapter;
import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.Retrofit.RestClient;
import com.example.mfekr.bakiongapp.Retrofit.RestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    private List<Recipe> mRecipeList;
    private RecyclerView mRecipeRecylcerView;
    private RecipeRecyclerViewAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeRecylcerView =   findViewById(R.id.recycler_view_recipe);
        mRecipeList = new ArrayList<>();
        mRecipeAdapter = new RecipeRecyclerViewAdapter(this, mRecipeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecipeRecylcerView.setLayoutManager(layoutManager);
        mRecipeRecylcerView.setHasFixedSize(true);
        mRecipeRecylcerView.setAdapter(mRecipeAdapter);

        RestInterface service = RestClient.getApiClient().create(RestInterface.class);
        Call<List<Recipe>> call = service.getListRecipe();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "cod: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Recipe> recipes = response.body();
                if (recipes == null){
                    Toast.makeText(MainActivity.this, "Something went wrong, please check your internet connection and try again! ", Toast.LENGTH_SHORT).show();
                }else {
                    mRecipeAdapter.setRecipes(recipes);
                }

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }



}
