package com.example.mfekr.bakiongapp.Adapters;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mfekr.bakiongapp.Model.Recipe;
import com.example.mfekr.bakiongapp.R;
import com.example.mfekr.bakiongapp.RecipesActivity;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder>{

    public static final String TAG = "RecipeRecyclerViewAdapter";
    public static final String LOG_TAG = RecipeRecyclerViewAdapter.class.getSimpleName();

    Context mContext;
    List<Recipe> mRecipes;

    public RecipeRecyclerViewAdapter(Context context, List<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_listitme, parent,false);
        RecipeViewHolder mviewViewHolder = new RecipeViewHolder(view);
        return mviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        holder.mRecipeName.setText(mRecipes.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe recipe = mRecipes.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("recipe", recipe);
                Intent i = new Intent(mContext,RecipesActivity.class);
                i.putExtras(bundle);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (!isNetworkAvailable()) {
            Toast.makeText(mContext, "Network not available!", Toast.LENGTH_SHORT).show();
        }
        return mRecipes.size();
    }

    public void setRecipes(List<Recipe> recipe){
        this.mRecipes = recipe;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{

        TextView mRecipeName;
        View mView;

        public RecipeViewHolder (View itemView) {
            super(itemView);
            mView = itemView;
            mRecipeName = itemView.findViewById(R.id.tv_recipe_name);

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}
