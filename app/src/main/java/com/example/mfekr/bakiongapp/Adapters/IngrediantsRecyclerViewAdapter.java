package com.example.mfekr.bakiongapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Model.Ingredient;

import com.example.mfekr.bakiongapp.R;

import java.util.List;

public class IngrediantsRecyclerViewAdapter extends RecyclerView.Adapter<IngrediantsRecyclerViewAdapter.ViewHolder> {


    List<Ingredient> mIngredients;
    Context mContext;

    public IngrediantsRecyclerViewAdapter(List<Ingredient> ingredients, Context context) {
        mIngredients = ingredients;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.ingrediants_list_item, parent,false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double quantity = mIngredients.get(position).getQuantity();
        String measure = mIngredients.get(position).getMeasure ();
        String ingredient= mIngredients.get(position).getIngredient ();

        String fullIngrediant = "• " + String.valueOf(quantity) + " " + measure + " " + ingredient;

        holder.ingradiantTV.setText (fullIngrediant);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingradiantTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ingradiantTV = itemView.findViewById(R.id.tv_ingrediant_list);

        }
    }
}
