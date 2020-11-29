package com.dodemy.room_bakingapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.room_bakingapp.R;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {

    private final Context context;
    private final ItemClickListener listener;
    private List<RecipeResponse> recipeResponseList;


    RecipeListAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        recipeResponseList = new ArrayList<>();
    }

    public void setList(List<RecipeResponse> recipes) {
        recipeResponseList = recipes;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recipeName.setText(recipeResponseList.get(position).getName());
        holder.servings.setText(context.getString(R.string.serving_label, recipeResponseList.get(position).getServings()));
    }

    @Override
    public int getItemCount() {
        if (recipeResponseList.size() > 0)
            return recipeResponseList.size();
        else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView recipeName;
        private TextView servings;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.list_recipe_name);
            servings = itemView.findViewById(R.id.list_recipe_servings);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onItemClick(recipeResponseList.get(position));
        }
    }

    interface ItemClickListener {
        void onItemClick(RecipeResponse recipeResponse);
    }
}
