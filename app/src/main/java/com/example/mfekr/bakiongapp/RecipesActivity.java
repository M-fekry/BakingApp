package com.example.mfekr.bakiongapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Fragments.RecipeDetailsFragment;
import com.example.mfekr.bakiongapp.Fragments.StepDetailsFragment;
import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity implements RecipeDetailsFragment.OnStepClickListener{

    private boolean mTwoPane;
    Recipe recipe;
    StepDetailsFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);



        Bundle bundle = getIntent().getExtras();
        recipe = bundle.getParcelable("recipe");
        bundle.putParcelable("recipe", recipe);
        RecipeDetailsFragment myfragment = new RecipeDetailsFragment();
        myfragment.setArguments(bundle);


//        sendDataToFragment(recipe);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.ingredient_container, myfragment)
                .commit();


        if (findViewById(R.id.steps_600dp) != null) {
            mTwoPane = true;

            FragmentManager detailFragmentManager = getSupportFragmentManager();

            fragment = new StepDetailsFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putParcelable("step",recipe.getSteps().get(0));
            fragment.setArguments(bundle1);
            detailFragmentManager.beginTransaction()
                    .add(R.id.step_details_container, fragment)
                    .commit();


        } else {
            mTwoPane = false;

        }



    }



    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public void onStepSelected(Step step) {

        if (mTwoPane) {
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("step", step);
//            mDetailsFragment = new RecipeDetailsFragment();
//            mDetailsFragment.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.step_details_container, mDetailsFragment)
//                    .commit();

            fragment.setupFragmentUI(step);
//

        } else {
            Intent intent = new Intent(this, StepsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("step", step);
            intent.putExtras(bundle);
            startActivity(intent);

        }

    }






}
