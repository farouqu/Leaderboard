package com.farouq.leaderboard;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.farouq.leaderboard.adapters.SkillListAdapter;
import com.farouq.leaderboard.models.TopSkill;
import com.farouq.leaderboard.services.APIClient;
import com.farouq.leaderboard.services.APIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SkillList extends Fragment {

    public SkillList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_layout,container,false);
        RecyclerView skillList = (RecyclerView) view.findViewById(R.id.recycler_view);
        skillList.setLayoutManager(new LinearLayoutManager(getContext()));

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<TopSkill>> topSkillRequest = apiInterface.getTopSkills();

        topSkillRequest.enqueue(new Callback<ArrayList<TopSkill>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<TopSkill>> call,@NotNull Response<ArrayList<TopSkill>> response) {
                SkillListAdapter adapter = new SkillListAdapter(getContext(),response.body());
                skillList.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<TopSkill>> call,@NotNull Throwable t) {
                Toast.makeText(getContext(),"Failed to get Top Skill IQ",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
