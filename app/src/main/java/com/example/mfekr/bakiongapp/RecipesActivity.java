package com.example.mfekr.bakiongapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Fragments.RecipeDetailsFragment;
import com.example.mfekr.bakiongapp.Fragments.StepDetailsFragment;
import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.Model.Step;

import java.util.List;

public class RecipesActivity extends AppCompatActivity{

    TextView mTextView;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);


        Bundle bundle = getIntent().getExtras();
        Recipe recipe = bundle.getParcelable("recipe");
        bundle.putParcelable("recipe", recipe);
        RecipeDetailsFragment myfragment = new RecipeDetailsFragment();
        myfragment.setArguments(bundle);


//        sendDataToFragment(recipe);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.ingredient_container, myfragment)
                .commit();

        if(findViewById(R.id.baking_linear_layout) != null){
            mTwoPane = true;

            if(savedInstanceState == null) {
                Bundle mbundle = getIntent().getExtras();

                Step step = mbundle.getParcelable("step");

                bundle.putParcelable("step", step);

                StepDetailsFragment fragment = new StepDetailsFragment();
                fragment.setArguments(mbundle);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .add(R.id.step_details_container, fragment)
                        .commit();
            }
        }else {
            mTwoPane =false;
        }

    }

//    public void sendDataToFragment(Recipe mrecipe){
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("recipe", mrecipe);
//        RecipeDetailsFragment myfragment = new RecipeDetailsFragment();
//        myfragment.setArguments(bundle);
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
//    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

//    @Override
//    public void onStepSelected(List<Step> steps, int position) {
//        Step step = steps.get(position);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("step", step);
//        Intent i = new Intent(this,RecipesActivity.class);
//        i.putExtras(bundle);
//        startActivity(i);
//    }
}
