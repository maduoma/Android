package com.dodemy.mvp.Model;

import com.dodemy.mvp.View.Product;

import java.util.List;

public interface IModel {


    List<Product> getListFromDatabase(String tableName);
}
