package com.example.mfekr.bakiongapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Model.Step;
import com.example.mfekr.bakiongapp.R;

import java.util.List;

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.StepsViewHolder> {



    List<Step> mSteps;

    StepItemClickListener mListener;
    Context mContext;

    public interface StepItemClickListener {
        void onStepItemClick(int position);
    }

    public StepsRecyclerViewAdapter(List<Step> mSteps,Context mContext, StepItemClickListener mListener) {
        this.mSteps = mSteps;
        this.mContext = mContext;
        this.mListener = mListener;

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
    public void onBindViewHolder(@NonNull final StepsViewHolder holder, int position) {


        //holder.tv_steps.setText(mSteps.get(position).getShortDescription());
        holder.bind(mSteps.get(position),mListener);


        ///
        //------------------------------------------------------------------------------//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Step step = mSteps.get(position);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("step", step);
//                Intent i = new Intent(mContext,StepsActivity.class);
//                i.putExtras(bundle);
//                mContext.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        final TextView tv_steps;


        public StepsViewHolder(View itemView) {
            super(itemView);

            tv_steps = itemView.findViewById(R.id.tv_steps_list);
           //itemView.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View view) {
//            int selectedPosition = getAdapterPosition();
//            mListener.onStepItemClick(selectedPosition);
//            notifyDataSetChanged();
//        }

        public void bind(final Step step, final StepItemClickListener listener) {
            tv_steps.setText(step.getShortDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int selectedPosition = getAdapterPosition();
                    listener.onStepItemClick(selectedPosition);
                }
            });
   }
   }

}

