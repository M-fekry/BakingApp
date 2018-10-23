package com.example.mfekr.bakiongapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mfekr.bakiongapp.Fragments.RecipeDetailsFragment;
import com.example.mfekr.bakiongapp.Model.Recipe;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);


        Bundle bundle = getIntent().getExtras();
        Recipe recipe = bundle.getParcelable("recipe");


        sendDataToFragment(recipe);

        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ingredient_container, recipeDetailsFragment)
                .commit();

    }

    public void sendDataToFragment(Recipe recipe){
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipeDetails", recipe);
        RecipeDetailsFragment myfragment = new RecipeDetailsFragment();
        myfragment.setArguments(bundle);
//        Intent intent = getIntent();
//        Recipe recipe = intent.getParcelableExtra("recipe");
//        Bundle bundle = new Bundle();
//        bundle.putParcelable ("recipeDetails", recipe);
//        RecipeDetailsFragment Recipe_Fragment = new RecipeDetailsFragment ();
//        Recipe_Fragment.setArguments(bundle);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frame_layout, Recipe_Fragment)
//                .commit();
    }
}
