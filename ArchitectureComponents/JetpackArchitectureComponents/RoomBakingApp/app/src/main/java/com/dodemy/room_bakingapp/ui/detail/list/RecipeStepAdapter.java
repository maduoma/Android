package com.dodemy.room_bakingapp.ui.detail.list;

import android.content.Context;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.room_bakingapp.R;
import com.dodemy.room_bakingapp.data.db.entities.Step;

import java.util.ArrayList;
import java.util.List;


public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.MyViewHolder> {
    private List<Step> stepList;
    private Context context;
    private StepClickListener listener;

    public RecipeStepAdapter(Context context, StepClickListener listener) {
        this.context = context;
        this.listener = listener;
        stepList = new ArrayList<>();
    }

    public void setList(List<Step> steps) {
        stepList = steps;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_recipe_item, viewGroup,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.stepTextView.setText(stepList.get(i).getShortDescription());
        if(TextUtils.isEmpty(stepList.get(i).getVideoURL())) {
            holder.playIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (stepList.size() > 0)
            return stepList.size();
        else return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stepTextView;
        ImageView playIcon;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stepTextView = itemView.findViewById(R.id.item_step_description);
            playIcon = itemView.findViewById(R.id.item_step_video_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onItemClick(stepList.get(position));
        }
    }

    interface StepClickListener {
        void onItemClick(Step step);
    }
}
