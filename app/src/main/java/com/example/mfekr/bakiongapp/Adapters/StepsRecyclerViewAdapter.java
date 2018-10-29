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


    private  final List<Step> mSteps;
    //private final Context mContext;
    private final StepItemClickListener listener;
  //p // private int mRowSelected;
   // private boolean mIsRowSelected;
   // private boolean mIsTwoPane;
    public int selectedPosition;

    public StepsRecyclerViewAdapter(List<Step> mSteps, StepItemClickListener listener) {
        this.mSteps = mSteps;
        this.listener = listener;
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


        holder.bind(position);
        

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

    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_steps;
        View mView;
        public StepsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_steps = itemView.findViewById(R.id.tv_steps_list);
            itemView.setOnClickListener(this);

        }

        ////
        //-----------------------------//
        private void bind(int listIndex){
            Step step = mSteps.get(listIndex);
            tv_steps.setText(step.getShortDescription());
        }
        @Override
        public void onClick(View view) {
            selectedPosition = getAdapterPosition();
            listener.onStepItemClick(selectedPosition);
            notifyDataSetChanged();
        }
    }


    public interface StepItemClickListener {
        void onStepItemClick(int rowSelected);
    }


}

