package com.dodemy.gadsaad;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.gadsaad.model.SkillIQ;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.PostViewHolder> {
    private List<SkillIQ> skillList = new ArrayList<>();
    private static final String TAG = "SkillIQAdapter";


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_iq_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {


        holder.skillName.setText(skillList.get(position).getName());
        holder.skillScore.setText(skillList.get(position).getScore() + " Skill IQ Score");
        holder.skillCountry.setText(skillList.get(position).getCountry());
        Picasso.get().load(skillList.get(position).getBadgeUrl()).into(holder.img);
        Log.d(TAG, "ImageUrl is : " + skillList.get(position).getBadgeUrl());
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public void setList(List<SkillIQ> list) {
        this.skillList = list;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView skillName, skillScore, skillCountry;
        ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.skillImg2);
            skillName = itemView.findViewById(R.id.skillName);
            skillScore = itemView.findViewById(R.id.skillScore);
            skillCountry = itemView.findViewById(R.id.skillCountry);
        }
    }
}