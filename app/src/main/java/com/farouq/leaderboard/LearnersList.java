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

import com.farouq.leaderboard.adapters.LearnersListAdapter;
import com.farouq.leaderboard.models.TopLearner;
import com.farouq.leaderboard.services.APIClient;
import com.farouq.leaderboard.services.APIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class LearnersList extends Fragment {

    public LearnersList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_layout,container,false);
        RecyclerView learnersList = (RecyclerView) view.findViewById(R.id.recycler_view);
        learnersList.setLayoutManager(new LinearLayoutManager(getContext()));

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<TopLearner>> topLearnersRequest = apiInterface.getTopLearners();

        topLearnersRequest.enqueue(new Callback<ArrayList<TopLearner>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<TopLearner>> call,@NotNull Response<ArrayList<TopLearner>> response) {
                LearnersListAdapter adapter = new LearnersListAdapter(getContext(),response.body());
                learnersList.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<TopLearner>> call, @NotNull Throwable t) {
                Toast.makeText(getContext(),"Failed to get Top Learners",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
