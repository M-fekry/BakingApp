package com.example.mfekr.bakiongapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mfekr.bakiongapp.Adapters.IngrediantsRecyclerViewAdapter;
import com.example.mfekr.bakiongapp.Adapters.StepsRecyclerViewAdapter;
import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.R;
import com.example.mfekr.bakiongapp.RecipesActivity;

public class RecipeDetailsFragment extends Fragment implements StepsRecyclerViewAdapter.StepItemClickListener{

    private RecyclerView mIngrediantsRecyclerView;
    private RecyclerView mStepsRecyclerView;
    private IngrediantsRecyclerViewAdapter mIngredientAdapter;
    private StepsRecyclerViewAdapter mStepsAdapter;


    Recipe recipe;
    OnStepClickListener mCallback;
    Context context;

    public interface OnStepClickListener{
        void onStepSelected(int position);
    }



    public RecipeDetailsFragment() {

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        if (args != null){
            recipe = args.getParcelable("recipe");
        }


        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
//        TextView textView = rootView.findViewById(R.id.tv_cakeName);
//        textView.setText(recipe.getName());

        mIngrediantsRecyclerView = rootView.findViewById(R.id.recycler_view_ingredients);
        setUpIngredientsList(mIngrediantsRecyclerView);

        mStepsRecyclerView = rootView.findViewById(R.id.recycler_view_steps);
        setUpStepsList(mStepsRecyclerView);


//        mIngredientAdapter = new IngrediantsRecyclerViewAdapter(recipe.getIngredients(),getContext());
//        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        mIngrediantsRecyclerView.setLayoutManager(mlayoutManager);
//        mIngrediantsRecyclerView.setHasFixedSize(true);
//        mIngrediantsRecyclerView.setAdapter(mIngredientAdapter);

        return rootView;
    }

    private void setUpIngredientsList(RecyclerView recyclerView) {
        mIngredientAdapter = new IngrediantsRecyclerViewAdapter(recipe.getIngredients(),getActivity());
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mIngredientAdapter);
    }

    private void setUpStepsList(RecyclerView recyclerView){
        mStepsAdapter = new StepsRecyclerViewAdapter(recipe.getSteps(), getActivity(), this);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mStepsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((RecipesActivity) getActivity())
                .setActionBarTitle(recipe.getName());
    }

    @Override
    public void onStepItemClick(int position) {
        mCallback.onStepSelected(position);
    }
}
