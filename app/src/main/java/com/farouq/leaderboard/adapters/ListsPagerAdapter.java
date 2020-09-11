package com.farouq.leaderboard.adapters;

import android.content.Context;

import com.farouq.leaderboard.LearnersList;
import com.farouq.leaderboard.R;
import com.farouq.leaderboard.SkillList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ListsPagerAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        mContext = context;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return mContext.getString(R.string.learners_tab_title);
        }else{
            return mContext.getString(R.string.skills_tab_title);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new LearnersList();
        }else{
            return new SkillList();
        }
    }
}
