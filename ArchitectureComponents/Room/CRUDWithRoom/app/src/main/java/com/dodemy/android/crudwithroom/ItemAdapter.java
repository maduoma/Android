package com.dodemy.android.crudwithroom;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;
    private LayoutInflater layoutInflater;
    private ItemDao mItemDao;

    public ItemAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(parent.getContext());
        mItemDao = db.itemDao();
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (itemList != null) {
            Item item = itemList.get(position);
            holder.itemText.setText(item.getItem());
        }
    }

    void setItems(List<Item> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        } else {
            return 0;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        ImageView deleteImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.itemTextView);
            deleteImage = itemView.findViewById(R.id.deleteImage);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(getAdapterPosition());
                }
            });
        }

        private Item getNoteAtPosition(int position) {
            return itemList.get(position);
        }

        private void delete(int position) {
            Item item = getNoteAtPosition(position);
            new deleteItemAsyncTask(mItemDao).execute(item);
        }
    }

    public static class deleteItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao mItemDao;

        deleteItemAsyncTask(ItemDao itemDao) {
            mItemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            mItemDao.delete(items[0]);
            return null;
        }
    }
}
