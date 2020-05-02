package com.dodemy.android.crudwithroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    public static final String ITEM = "item";

    private EditText itemEditText;
    private List<Item> itemList;
    private ItemDao mItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemEditText = findViewById(R.id.textInputEditText);
        Button saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList = new ArrayList<>();
                save();
            }
        });
    }

    private void save() {
        if (isEditTextFilled()) {
            String itemToBeAdded = itemEditText.getText().toString().trim();
            Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
            intent.putExtra(ITEM, itemToBeAdded);
            Item item = new Item(itemToBeAdded);

            ItemRoomDatabase db = ItemRoomDatabase.getDatabase(AddItemActivity.this);
            mItemDao = db.itemDao();

            new insertItemAsyncTask(mItemDao).execute(item);

            startActivity(intent);
            finish();
        }
    }

    private boolean isEditTextFilled() {
        if (TextUtils.isEmpty(itemEditText.getText().toString().trim())) {
            itemEditText.setError("Cannot save empty item");
            return false;
        }
        return true;
    }

    private static class insertItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao itemDao;

        insertItemAsyncTask(ItemDao dao) {
            itemDao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insertItem(items[0]);
            return null;
        }
    }
}
