package com.example.mfekr.bakiongapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Model.Step;
import com.example.mfekr.bakiongapp.R;
import com.example.mfekr.bakiongapp.StepsActivity;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

public class StepDetailsFragment extends Fragment {

    private Step mStep;
    private PlayerView mPlayerView;
    TextView tvDecription;
    ImageButton mButtonNext , mButtonPrev;
    RecipeDetailsFragment.OnStepSelectedListener mCallback;
    private ArrayList<Step> steps;


    public StepDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        if (args != null){
            mStep = args.getParcelable("step");
        }


        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);

        tvDecription = rootView.findViewById(R.id.tv_description);
        tvDecription.setText(mStep.getDescription());

        mPlayerView = rootView.findViewById(R.id.playerView);

        mButtonNext = rootView.findViewById(R.id.btn_next);
        mButtonPrev = rootView.findViewById(R.id.btn_prev);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((StepsActivity) getActivity())
                .setActionBarTitle(mStep.getShortDescription());
    }

//    private void setUpDetailsFragment(){
//        tvDecription.setText(mStep.getDescription());
//    }
}
