package com.farouq.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farouq.leaderboard.R;
import com.farouq.leaderboard.models.TopLearner;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearnersListAdapter extends RecyclerView.Adapter<LearnersListAdapter.LearnerHolder> {
    private ArrayList<TopLearner> topLearnersList;
    private Context currentContext;


    public LearnersListAdapter(Context context, ArrayList<TopLearner> learnerList) {
        topLearnersList = learnerList;
        currentContext = context;
    }

    @NonNull
    @Override
    public LearnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(currentContext);
        View itemView = inflater.inflate(R.layout.learner_list_item,parent,false);
        return new LearnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerHolder holder, int position) {
        TopLearner learner = topLearnersList.get(position);
        holder.mLearnerName.setText(learner.getName());
        holder.mLearnerDetails.setText(learner.getHours() + " " + currentContext.getString(R.string.learning_hours_completer) + " " + learner.getCountry());
    }

    @Override
    public int getItemCount() {
        return topLearnersList.size();
    }

    public class LearnerHolder extends RecyclerView.ViewHolder{
        public final TextView mLearnerName;
        public final TextView mLearnerDetails;

        public LearnerHolder(View itemView){
            super(itemView);
            mLearnerName = itemView.findViewById(R.id.learner_title);
            mLearnerDetails = itemView.findViewById(R.id.learner_details);
        }
    }


}
