package com.dodemy.gadsproject_aad;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dodemy.gadsproject_aad.model.TopLearner;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class TopLearnersAdapter extends RecyclerView.Adapter<TopLearnersAdapter.learnerViewHolder> {
    private List<TopLearner> topLearnersList = new ArrayList<>();
    Context context;

    public TopLearnersAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public learnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new learnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_learner_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull learnerViewHolder holder, int position) {
        holder.learnersName.setText(topLearnersList.get(position).getName());
        holder.skillHour.setText(MessageFormat.format("{0} Learning hours ,", String.valueOf(topLearnersList.get(position).getHours())));
        holder.learnersCountry.setText(topLearnersList.get(position).getCountry());
        Glide.with(context).load(topLearnersList.get(position).getBadgeUrl()).into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return topLearnersList.size();
    }

    public void setList(List<TopLearner> list) {
        this.topLearnersList = list;
        notifyDataSetChanged();
    }

    public static class learnerViewHolder extends RecyclerView.ViewHolder {
        TextView learnersName, skillHour, learnersCountry;
        ImageView img2 ;
        public learnerViewHolder(@NonNull View itemView) {
            super(itemView);
            img2= itemView.findViewById(R.id.learnersImg);
            learnersName = itemView.findViewById(R.id.learnersName);
            skillHour = itemView.findViewById(R.id.learnersHour);
            learnersCountry = itemView.findViewById(R.id.learnersCountry);
        }
    }
}