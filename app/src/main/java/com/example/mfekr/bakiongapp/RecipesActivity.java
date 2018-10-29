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

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity implements RecipeDetailsFragment.OnStepClickListener{

    TextView mTextView;
    private boolean mTwoPane;
    Recipe recipe;
    //private ArrayList<Step> mSteps;


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
//    public void onStepSelected(int position) {
//        Step step = recipe.getSteps().get(position);
//
//        if (mTwoPane){
//            StepDetailsFragment detailFragment = new StepDetailsFragment();
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("step", step);
//            detailFragment.setArguments(bundle);
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.step_details_containe, detailFragment)
//                    .commit();
//        } else {
//            Intent i = new Intent(this,StepsActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("step", step);
//            i.putExtras(bundle);
//            startActivity(i);
//        }
//    }

    @Override
    public void onStepSelected(int position) {
        Step step = recipe.getSteps().get(position);

//        if (findViewById(R.id.steps_600dp) != null) {
////            loadFragmentsForDetailPane(step);
////        } else {
////           step = mSteps.get(getStepPos(step));
////                Bundle bundle = new Bundle();
////                bundle.putParcelable("step", step);
////                Intent i = new Intent(this,StepsActivity.class);
////                i.putExtras(bundle);
////                startActivity(i);
////        }
        if (mTwoPane){
            StepDetailsFragment detailFragment = new StepDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("step", step);
            detailFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.step_details_container, detailFragment)
                    .commit();
        }
        else {
            Intent intent = new Intent(this, StepsActivity.class);
            intent.putExtra("step", step);
            startActivity(intent);
        }
    }

//    private int getStepPos(Step step) {
////        return mSteps.indexOf(step);
////    }

    void loadFragmentsForDetailPane(Step step) {
        StepDetailsFragment detailFragment = new StepDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("step", step);
        detailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, detailFragment)
                .commit();
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
