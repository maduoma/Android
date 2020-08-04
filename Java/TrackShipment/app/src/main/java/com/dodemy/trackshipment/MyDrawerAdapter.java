package com.dodemy.trackshipment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter class of NavigationDrawerFragment.
 *
 */
public class MyDrawerAdapter extends BaseAdapter {

    private Context context;
    private String[] titles;
    private int[] images;
    private LayoutInflater inflater;
    private int[] selectedPosition;

    public MyDrawerAdapter(Context context, String[] titles, int[] images,
                           int[] selectedPosition) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(this.context);
        this.selectedPosition = selectedPosition;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.drawer_list, null);
            mViewHolder = new ViewHolder();
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.tvTitle = (TextView) convertView
                .findViewById(R.id.textView1);
        mViewHolder.ivIcon = (ImageView) convertView
                .findViewById(R.id.imageView1);

        mViewHolder.tvTitle.setText(titles[position]);
        mViewHolder.ivIcon.setImageResource(images[position]);

        //Highlight the selected list item
        if (position == selectedPosition[0]) {
            convertView.setBackgroundColor(Color.parseColor("#24b5b8"));
            mViewHolder.tvTitle.setTextColor(Color.BLACK);
        }
        else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
            mViewHolder.tvTitle.setTextColor(Color.WHITE);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView tvTitle;
        ImageView ivIcon;
    }
}
