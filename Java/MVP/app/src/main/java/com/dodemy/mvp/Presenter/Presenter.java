package com.dodemy.mvp.Presenter;



import androidx.fragment.app.FragmentActivity;

import com.dodemy.mvp.Model.IModel;
import com.dodemy.mvp.Model.Model;
import com.dodemy.mvp.View.ConstantsOfApp;
import com.dodemy.mvp.View.IView;
import com.dodemy.mvp.View.Product;
import com.dodemy.mvp.View.Fragments.StringsFragment;

import java.util.List;

public class Presenter implements IPresenter {

    private IView iView;
    private IModel iModel;

    public Presenter(FragmentActivity activity) {
        iView = StringsFragment.newInstance("a", "b");
        iModel = new Model(activity.getApplicationContext());
    }

    @Override
    public List<Product> getListStrings() {

        return iModel.getListFromDatabase(ConstantsOfApp.STRING);
    }

    @Override
    public List<Product> getListInteger() {

        return iModel.getListFromDatabase(ConstantsOfApp.INTEGER);
    }

    @Override
    public List<Product> getListArrays() {

        return iModel.getListFromDatabase(ConstantsOfApp.ARRAYS);
    }

    @Override
    public List<Product> getListStringbuilder() {

        return iModel.getListFromDatabase(ConstantsOfApp.STRINGBUILDER);
    }

    @Override
    public List<Product> getListCalendar() {

        return iModel.getListFromDatabase(ConstantsOfApp.CALENDAR);
    }

    @Override
    public List<Product> getListShort() {

        return iModel.getListFromDatabase(ConstantsOfApp.SHORT);
    }

    @Override
    public List<Product> getListByte() {

        return iModel.getListFromDatabase(ConstantsOfApp.BYTE);
    }

    @Override
    public List<Product> getListBoolean() {

        return iModel.getListFromDatabase(ConstantsOfApp.BOOLEAN);
    }

    @Override
    public List<Product> getListLong() {

        return iModel.getListFromDatabase(ConstantsOfApp.LONG);
    }

    @Override
    public List<Product> getListDouble() {

        return iModel.getListFromDatabase(ConstantsOfApp.DOUBLE);
    }

    @Override
    public List<Product> getListFloat() {

        return iModel.getListFromDatabase(ConstantsOfApp.FLOAT);
    }

    @Override
    public List<Product> getListCharacter() {

        return iModel.getListFromDatabase(ConstantsOfApp.CHARACTER);
    }

    @Override
    public List<Product> getListArrayList() {

        return iModel.getListFromDatabase(ConstantsOfApp.ARRAYLIST);
    }


}