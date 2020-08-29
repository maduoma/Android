package com.dodemy.mvp.View;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.mvp.R;
import com.dodemy.mvp.View.DetailedActivity.View.DetailedActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<Product> productList = new ArrayList<>();
    private Product product;


    public MyAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        product = productList.get(position);

        holder.mTvType.setText(product.getType());
        holder.mTvMethod.setText(product.getMethod());
        holder.mTvDescription.setText(product.getDescription());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTvType;
        TextView mTvMethod;
        TextView mTvDescription;
        RelativeLayout relativeLayout;
        TabLayout tabLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTvType = (TextView) itemView.findViewById(R.id.tv_type);
            this.mTvMethod = (TextView) itemView.findViewById(R.id.tv_method);
            this.mTvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout_items);
            this.tabLayout = itemView.findViewById(R.id.tabs);

            mTvType.setOnClickListener(this);
            mTvMethod.setOnClickListener(this);
            mTvDescription.setOnClickListener(this);
            relativeLayout.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Log.i("autolog", "getAdapterPosition(): " + getAdapterPosition());

            Intent intent = new Intent(view.getContext(), DetailedActivity.class);
            intent.putExtra(ConstantsOfApp.TYPE, mTvType.getText().toString());
            intent.putExtra(ConstantsOfApp.METHOD, mTvMethod.getText().toString());
            intent.putExtra(ConstantsOfApp.DESCRIPTION, mTvDescription.getText().toString());
            intent.putExtra(ConstantsOfApp.OBJECT, product.getObject());
            view.getContext().startActivity(intent);

        }
    }
}
