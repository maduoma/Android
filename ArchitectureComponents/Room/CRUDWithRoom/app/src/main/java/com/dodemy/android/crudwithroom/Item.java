package com.dodemy.android.crudwithroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "item")
    private String item;

    public Item(int id, String item) {
        this.id = id;
        this.item = item;
    }

    @Ignore
    public Item(String item) {
        this.item = item;
    }

    public int getId() {
        return this.id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
