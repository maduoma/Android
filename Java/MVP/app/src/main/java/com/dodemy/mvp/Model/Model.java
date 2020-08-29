package com.dodemy.mvp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dodemy.mvp.View.DatabaseOpenHelper;
import com.dodemy.mvp.View.Product;

import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {
    private SQLiteDatabase database;

    public Model(Context context) {
        database = new DatabaseOpenHelper(context).getWritableDatabase();
    }

    @Override
    public List<Product> getListFromDatabase(String tableName) {

        List<Product> list = new ArrayList<>();
        String sqlQueryText = "SELECT * FROM " + tableName;

        Cursor cursor = this.database.rawQuery(sqlQueryText, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Product(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return list;
    }

}