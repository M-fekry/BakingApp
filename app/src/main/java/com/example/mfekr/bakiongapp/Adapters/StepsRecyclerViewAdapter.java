package com.example.mfekr.bakiongapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Model.Step;
import com.example.mfekr.bakiongapp.R;
import com.example.mfekr.bakiongapp.StepsActivity;

import java.util.List;

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.StepsViewHolder> {


    List<Step> mSteps;
    Context mContext;

    public StepsRecyclerViewAdapter(List<Step> steps, Context context) {
        mSteps = steps;
        mContext = context;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.steps_list_item, parent,false);
        StepsViewHolder mViewHolder = new StepsViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, final int position) {


        holder.tv_steps.setText(mSteps.get(position).getShortDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Step step = mSteps.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("step", step);
                Intent i = new Intent(mContext,StepsActivity.class);
                i.putExtras(bundle);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_steps;
        View mView;
        public StepsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
             tv_steps = itemView.findViewById(R.id.tv_steps_list);

        }
    }
}

