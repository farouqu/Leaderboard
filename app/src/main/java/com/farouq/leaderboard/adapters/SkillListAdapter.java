package com.farouq.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farouq.leaderboard.R;
import com.farouq.leaderboard.models.TopSkill;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillHolder>{
    private ArrayList<TopSkill> topSkillsList;
    private Context currentContext;


    public SkillListAdapter(Context context, ArrayList<TopSkill> skillsList) {
        topSkillsList = skillsList;
        currentContext = context;
    }

    @NonNull
    @Override
    public SkillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(currentContext);
        View itemView = inflater.inflate(R.layout.skill_list_item,parent,false);
        return new SkillHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillHolder holder, int position) {
        TopSkill skill = topSkillsList.get(position);
        holder.mLearnerName.setText(skill.getName());
        holder.mLearnerDetails.setText(skill.getScore() + " " + currentContext.getString(R.string.skill_score_completer) + " " + skill.getCountry());
    }

    @Override
    public int getItemCount() {
        return topSkillsList.size();
    }

    public class SkillHolder extends RecyclerView.ViewHolder{
        public final TextView mLearnerName;
        public final TextView mLearnerDetails;

        public SkillHolder(View itemView){
            super(itemView);
            mLearnerName = itemView.findViewById(R.id.skill_title);
            mLearnerDetails = itemView.findViewById(R.id.skill_details);
        }
    }

}
