package com.dodemy.mvp.View.DetailedActivity.Presenter;

public interface IPresenter {

    void setViewsVisibility();

    void onEtEnterValueDeleteClicked();

    void onEtParam1DeleteClicked();

    void onEtParam2DeleteClicked();

    void onEtParam3DeleteClicked();

    void onEtParam4DeleteClicked();

    void onEtParam5DeleteClicked();

    void onEtParam6DeleteClicked();

    void onEtParam7DeleteClicked();

    void onEtParam8DeleteClicked();


    void onEtEnterValueQuoteClicked();

    void onmEtParam1QuoteClicked();

    void onmEtParam2QuoteClicked();

    void onmEtParam3QuoteClicked();

    void onmEtParam4QuoteClicked();

    void onmEtParam5QuoteClicked();

    void onmEtParam6QuoteClicked();

    void onmEtParam7QuoteClicked();

    void onmEtParam8QuoteClicked();

    //execute java code using BeenShell library
    void onExecuteCodeFromTv();

    void onSetInitialTextToEnterValue();

    void onFormCodeAndSetToTv();

    void onFormTextFromParams();

    void setInitialTextToParams();

    void setSplitParamsArray();

    void setDataFromPreviousActivity();

    void setDataToViewsReceivedFromPreviousActivity();

    void setEditTextChangedListener();

    void setSplitParamsArrayOnlyType();
}
