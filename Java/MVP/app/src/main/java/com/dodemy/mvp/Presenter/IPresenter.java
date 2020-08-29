package com.dodemy.mvp.Presenter;

import com.dodemy.mvp.View.Product;

import java.util.List;

public interface IPresenter {


    List<Product> getListStrings();

    List<Product> getListInteger();

    List<Product> getListArrays();

    List<Product> getListStringbuilder();

    List<Product> getListCalendar();

    List<Product> getListShort();

    List<Product> getListByte();

    List<Product> getListBoolean();

    List<Product> getListLong();

    List<Product> getListDouble();

    List<Product> getListFloat();

    List<Product> getListCharacter();

    List<Product> getListArrayList();
}
