package com.dodemy.gadsprojectwithretrofit;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.gadsprojectwithretrofit.Models.LearningLeaders;
import com.dodemy.gadsprojectwithretrofit.Models.SkillIQLeaders;

public class LeadersViewHolder extends RecyclerView.ViewHolder {
    ImageView mLeaderBadge;
    TextView mLeaderName, mLeaderDetails;

    public LeadersViewHolder(@NonNull View itemView) {
        super(itemView);

        mLeaderBadge = itemView.findViewById(R.id.leader_badge_image);
        mLeaderName = itemView.findViewById(R.id.leader_name);
        mLeaderDetails = itemView.findViewById(R.id.leader_details);
    }

    public void bind(SkillIQLeaders learningLeaders) {
        String details = learningLeaders.getScore() + " skill IQ score, " + learningLeaders.getCountry();
        mLeaderName.setText(learningLeaders.getName());
        mLeaderDetails.setText(details);
        Utilities.ImagesUtility.loadImage(mLeaderBadge, learningLeaders.getBadgeUrl());
    }

    public void bind(LearningLeaders learningLeaders) {
        String details = learningLeaders.getHours() + " Hours, " + learningLeaders.getCountry();
        mLeaderName.setText(learningLeaders.getName());
        mLeaderDetails.setText(details);
        Utilities.ImagesUtility.loadImage(mLeaderBadge, learningLeaders.getBadgeUrl());
    }

}