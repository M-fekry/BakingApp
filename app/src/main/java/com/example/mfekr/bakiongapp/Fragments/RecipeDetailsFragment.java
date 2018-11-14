package com.example.mfekr.bakiongapp.Fragments;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mfekr.bakiongapp.Adapters.IngrediantsRecyclerViewAdapter;
import com.example.mfekr.bakiongapp.Adapters.StepsRecyclerViewAdapter;
import com.example.mfekr.bakiongapp.BuildConfig;
import com.example.mfekr.bakiongapp.Model.Ingredient;
import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.Model.Step;
import com.example.mfekr.bakiongapp.R;
import com.example.mfekr.bakiongapp.RecipesActivity;
import com.example.mfekr.bakiongapp.Widget.BakingAppWidgetProvider;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment implements StepsRecyclerViewAdapter.StepItemClickListener {

    private RecyclerView mIngrediantsRecyclerView;
    private RecyclerView mStepsRecyclerView;
    private IngrediantsRecyclerViewAdapter mIngredientAdapter;
    private StepsRecyclerViewAdapter mStepsAdapter;
    private Ingredient mIngredientData;
    private ArrayList<String> mIngredients;
    private ArrayList<String> mMeasures;
    private float[] mQuantities;
    Recipe recipe;
    OnStepClickListener mCallback;
    Context context;
    Button widgetBtn;
    Gson mGson;

    SharedPreferences sharedPref;

    public interface OnStepClickListener {
        void onStepSelected(Step position);
    }


    public RecipeDetailsFragment() {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnStepClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        if (args != null) {
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

        widgetBtn = rootView.findViewById(R.id.btn_widget);
        widgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref = getActivity().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
                boolean isRecipeInWidget = (sharedPref.getInt("ID", -1) == recipe.getId());
                if (isRecipeInWidget) {
                    sharedPref.edit()
                            .remove("ID")
                            .remove("WIDGET_TITLE")
                            .remove("WIDGET_INGREDIENT")
                            .apply();

                } else{
                    sharedPref.edit()
                            .putInt("ID", recipe.getId())
                            .putString("WIDGET_TITLE", recipe.getName())
                            .putString("WIDGET_INGREDIENT", ingredientsStringBuilder())
                            .apply();
            }

            }
        });
        ComponentName provider = new ComponentName(getActivity(), BakingAppWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity());
        int[] ids = appWidgetManager.getAppWidgetIds(provider);
        BakingAppWidgetProvider bakingWidgetProvider = new BakingAppWidgetProvider();
        bakingWidgetProvider.onUpdate(getActivity(), appWidgetManager, ids);

        return rootView;
    }

    private String ingredientsStringBuilder() {
        StringBuilder ingredient = new StringBuilder();

        for (Ingredient ingredients : recipe.getIngredients()) {
            ingredient.append(String.valueOf(ingredients.getIngredient())).append("  ")
                    .append(ingredients.getMeasure()).append("  ")
                    .append(String.valueOf(ingredients.getQuantity())).append("\n");
        }
        return ingredient.toString();
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
    public void onStepItemClick(Step position) {
        mCallback.onStepSelected(position);
    }

}

