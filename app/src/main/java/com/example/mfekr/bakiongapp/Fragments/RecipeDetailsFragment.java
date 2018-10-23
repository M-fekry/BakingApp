package com.example.mfekr.bakiongapp.Fragments;


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
import com.example.mfekr.bakiongapp.Model.Ingredient;
import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.R;

import java.util.List;

public class RecipeDetailsFragment extends Fragment{

    private RecyclerView mIngrediantsRecyclerView;
    private IngrediantsRecyclerViewAdapter mIngredientAdapter;
    private List<Ingredient> mIngredients;
//    private Recipe recipe;




    public RecipeDetailsFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        Recipe recipe = args.getParcelable("recipeDetails");

        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        mIngrediantsRecyclerView = rootView.findViewById(R.id.recycler_view_ingredients);
        mIngredientAdapter = new IngrediantsRecyclerViewAdapter(recipe.getIngredients(),getContext());
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mIngrediantsRecyclerView.setLayoutManager(mlayoutManager);
        mIngrediantsRecyclerView.setHasFixedSize(true);
        mIngrediantsRecyclerView.setAdapter(mIngredientAdapter);

        return rootView;
    }

//    private void setUpIngredientsList(RecyclerView recyclerView) {
//        mIngredientAdapter = new IngrediantsRecyclerViewAdapter(recipe.getIngredients(),getContext());
//        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        mIngrediantsRecyclerView.setLayoutManager(mlayoutManager);
//        mIngrediantsRecyclerView.setHasFixedSize(true);
//        mIngrediantsRecyclerView.setAdapter(mIngredientAdapter);
//
//
//    }
}
