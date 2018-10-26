package com.example.mfekr.bakiongapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mfekr.bakiongapp.Fragments.StepDetailsFragment;
import com.example.mfekr.bakiongapp.Model.Step;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Bundle bundle = getIntent().getExtras();
        Step step = bundle.getParcelable("step");
        bundle.putParcelable("step", step);
        StepDetailsFragment myfragment = new StepDetailsFragment();
        myfragment.setArguments(bundle);


//        sendDataToFragment(recipe);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, myfragment)
                .commit();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
