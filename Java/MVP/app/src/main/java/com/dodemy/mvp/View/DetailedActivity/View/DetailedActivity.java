package com.dodemy.mvp.View.DetailedActivity.View;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.dodemy.mvp.R;
import com.dodemy.mvp.View.ConstantsOfApp;
import com.dodemy.mvp.View.DetailedActivity.Presenter.IPresenter;
import com.dodemy.mvp.View.DetailedActivity.Presenter.Presenter;
import com.dodemy.mvp.View.MakeAnimation;

public class DetailedActivity extends AppCompatActivity implements View.OnClickListener, IView {


    private TextView mTvMethod;
    private TextView mTvType;
    private TextView mTvDescription;

    private EditText mEtParam1;
    private EditText mEtParam2;
    private EditText mEtParam3;
    private EditText mEtParam4;
    private EditText mEtParam5;
    private EditText mEtParam6;
    private EditText mEtParam7;
    private EditText mEtParam8;
    private TextView mTvResult;
    private EditText mEtEnterValue;
    private TextView mEnterParamsTv;
    private TextView mEnterValueTv;
    private View mLineParamsView;
    private ImageView mQuotesIv;
    private ImageView mDeleteIv;
    private ImageView mQuotesParam1Iv;
    private ImageView mDeleteParam1Iv;
    private ImageView mQuotesParam2Iv;
    private ImageView mDeleteParam2Iv;
    private ImageView mQuotesParam3Iv;
    private ImageView mDeleteParam3Iv;
    private ImageView mQuotesParam4Iv;
    private ImageView mDeleteParam4Iv;
    private ImageView mQuotesParam5Iv;
    private ImageView mDeleteParam5Iv;
    private ImageView mQuotesParam6Iv;
    private ImageView mDeleteParam6Iv;
    private ImageView mQuotesParam7Iv;
    private ImageView mDeleteParam7Iv;
    private ImageView mQuotesParam8Iv;
    private ImageView mDeleteParam8Iv;
    private EditText mCodeEt;
    private String[] splitParamsArray;
    private String[] splitParamsArrayOnlyType;
    private String getIntentMethod;
    private String getIntentDescription;
    private String getIntentType;
    private String getIntentObject;
    private String tempParamsText = "";

    private IPresenter presenter;

    //form the code from different variables
    //findViewById ALL views

    private void initView() {
        mTvMethod = (TextView) findViewById(R.id.tv_method);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mTvDescription = (TextView) findViewById(R.id.tv_description);
        mEtParam1 = (EditText) findViewById(R.id.et_param1);
        mEtParam2 = (EditText) findViewById(R.id.et_param2);
        mEtParam3 = (EditText) findViewById(R.id.et_param3);
        mEtParam4 = (EditText) findViewById(R.id.et_param4);
        mEtParam5 = (EditText) findViewById(R.id.et_param5);
        mEtParam6 = (EditText) findViewById(R.id.et_param6);
        mEtParam7 = (EditText) findViewById(R.id.et_param7);
        mEtParam8 = (EditText) findViewById(R.id.et_param8);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mEtEnterValue = (EditText) findViewById(R.id.et_enter_value);
        mEnterParamsTv = (TextView) findViewById(R.id.tv_enter_params);
        mLineParamsView = (View) findViewById(R.id.view_line_params);
        mCodeEt = findViewById(R.id.et_code);
        mQuotesIv = (ImageView) findViewById(R.id.iv_quotes);
        mQuotesIv.setOnClickListener(this);
        mDeleteIv = (ImageView) findViewById(R.id.iv_delete);
        mDeleteIv.setOnClickListener(this);

        mEnterValueTv = (TextView) findViewById(R.id.tv_enter_value);
        mQuotesParam1Iv = (ImageView) findViewById(R.id.iv_quotes_param1);
        mDeleteParam1Iv = (ImageView) findViewById(R.id.iv_delete_param1);
        mQuotesParam2Iv = (ImageView) findViewById(R.id.iv_quotes_param2);
        mDeleteParam2Iv = (ImageView) findViewById(R.id.iv_delete_param2);
        mQuotesParam3Iv = (ImageView) findViewById(R.id.iv_quotes_param3);
        mDeleteParam3Iv = (ImageView) findViewById(R.id.iv_delete_param3);
        mQuotesParam4Iv = (ImageView) findViewById(R.id.iv_quotes_param4);
        mDeleteParam4Iv = (ImageView) findViewById(R.id.iv_delete_param4);
        mQuotesParam5Iv = (ImageView) findViewById(R.id.iv_quotes_param5);
        mDeleteParam5Iv = (ImageView) findViewById(R.id.iv_delete_param5);
        mQuotesParam6Iv = (ImageView) findViewById(R.id.iv_quotes_param6);
        mDeleteParam6Iv = (ImageView) findViewById(R.id.iv_delete_param6);
        mQuotesParam7Iv = (ImageView) findViewById(R.id.iv_quotes_param7);
        mDeleteParam7Iv = (ImageView) findViewById(R.id.iv_delete_param7);
        mQuotesParam8Iv = (ImageView) findViewById(R.id.iv_quotes_param8);
        mDeleteParam8Iv = (ImageView) findViewById(R.id.iv_delete_param8);


        mQuotesParam1Iv.setOnClickListener(this);
        mDeleteParam1Iv.setOnClickListener(this);
        mQuotesParam2Iv.setOnClickListener(this);
        mDeleteParam2Iv.setOnClickListener(this);
        mQuotesParam3Iv.setOnClickListener(this);
        mDeleteParam3Iv.setOnClickListener(this);
        mQuotesParam4Iv.setOnClickListener(this);
        mDeleteParam4Iv.setOnClickListener(this);
        mQuotesParam5Iv.setOnClickListener(this);
        mDeleteParam5Iv.setOnClickListener(this);
        mQuotesParam6Iv.setOnClickListener(this);
        mDeleteParam6Iv.setOnClickListener(this);
        mQuotesParam7Iv.setOnClickListener(this);
        mDeleteParam7Iv.setOnClickListener(this);
        mQuotesParam8Iv.setOnClickListener(this);
        mDeleteParam8Iv.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        initView();

        presenter = new Presenter(this);

        presenter.setDataFromPreviousActivity();
        presenter.setDataToViewsReceivedFromPreviousActivity();
        presenter.setSplitParamsArray();
        presenter.setSplitParamsArrayOnlyType();
        presenter.setEditTextChangedListener();
        presenter.setViewsVisibility();
        presenter.setInitialTextToParams();
        presenter.onSetInitialTextToEnterValue();
        presenter.onFormCodeAndSetToTv();
        presenter.onExecuteCodeFromTv();


    }

    @Override
    public String[] getSplitParamsArrayOnlyType() {
        return splitParamsArrayOnlyType;
    }

    @Override
    public void setSplitParamsArrayOnlyType(String[] splitParamsArrayOnlyType) {
        this.splitParamsArrayOnlyType = splitParamsArrayOnlyType;
    }

    @Override
    public void setEditTextChangedListener() {
        editTextChangedListener(mEtEnterValue);
        editTextChangedListener(mEtParam1);
        editTextChangedListener(mEtParam2);
        editTextChangedListener(mEtParam3);
        editTextChangedListener(mEtParam4);
        editTextChangedListener(mEtParam5);
        editTextChangedListener(mEtParam6);
        editTextChangedListener(mEtParam7);
    }

    @Override
    public void setDataToViewsReceivedFromPreviousActivity() {
        mTvType.setText(getIntentType);
        mTvMethod.setText(getIntentMethod);
        mTvDescription.setText(getIntentDescription);
    }

    @Override
    public void setDataFromPreviousActivity() {
        getIntentMethod = getIntent().getStringExtra(ConstantsOfApp.METHOD);
        getIntentDescription = getIntent().getStringExtra(ConstantsOfApp.DESCRIPTION);
        getIntentType = getIntent().getStringExtra(ConstantsOfApp.TYPE);
        getIntentObject = getIntent().getStringExtra(ConstantsOfApp.OBJECT);
    }

    @Override
    public void setSplitParamsArray(String[] strings) {
        splitParamsArray = strings;
    }

    private void editTextChangedListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                presenter.onFormTextFromParams();
                presenter.onFormCodeAndSetToTv();
                presenter.onExecuteCodeFromTv();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public String getTextCodeEt() {
        return mCodeEt.getText().toString();
    }

    private void makeAnimation(int viewId, View v) {
        MakeAnimation.makeAnimationOnView(viewId, Techniques.FadeOut, 150, 0, v);
        MakeAnimation.makeAnimationOnView(viewId, Techniques.FadeIn, 350, 0, v);

    }

    @Override
    public String[] getSplitParamsArray() {
        return splitParamsArray;
    }

    @Override
    public String getTextTvType() {
        return mTvType.getText().toString();
    }

    @Override
    public String getTextTvMethod() {
        return mTvMethod.getText().toString();
    }

    @Override
    public String getTextEtEnterValue() {
        return mEtEnterValue.getText().toString();
    }

    @Override
    public String getTextGetIntentObject() {
        return getIntentObject;
    }

    @Override
    public String getTextGetIntentDescription() {
        return getIntentDescription;
    }

    @Override
    public String getTextGetIntentMethod() {
        return getIntentMethod;
    }

    @Override
    public String getTextGetIntentType() {
        return getIntentType;
    }

    @Override
    public String getTextTempParamsText() {
        return tempParamsText;
    }

    @Override
    public void setTextTempParamsText(String s) {
        tempParamsText = s;
    }

    @Override
    public void setTextCodeEt(String code) {
        mCodeEt.setText(code);
    }

    @Override
    public void setTextTvResult(String s) {
        mTvResult.setText(s);
    }

    @Override
    public void setTextEtEnterValue(String s) {
        mEtEnterValue.setText(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_quotes:
                makeAnimation(R.id.iv_quotes, v);
                presenter.onEtEnterValueQuoteClicked();
                break;

            case R.id.iv_delete:
                makeAnimation(R.id.iv_delete, v);
                presenter.onEtEnterValueDeleteClicked();
                break;

            case R.id.iv_quotes_param1:
                makeAnimation(R.id.iv_quotes_param1, v);
                presenter.onmEtParam1QuoteClicked();
                break;

            case R.id.iv_delete_param1:
                makeAnimation(R.id.iv_delete_param1, v);
                presenter.onEtParam1DeleteClicked();
                break;

            case R.id.iv_quotes_param2:
                makeAnimation(R.id.iv_quotes_param2, v);
                presenter.onmEtParam2QuoteClicked();
                break;

            case R.id.iv_delete_param2:
                makeAnimation(R.id.iv_delete_param2, v);
                presenter.onEtParam2DeleteClicked();
                break;

            case R.id.iv_quotes_param3:
                makeAnimation(R.id.iv_quotes_param3, v);
                presenter.onmEtParam3QuoteClicked();
                break;

            case R.id.iv_delete_param3:
                makeAnimation(R.id.iv_delete_param3, v);
                presenter.onEtParam3DeleteClicked();
                break;

            case R.id.iv_quotes_param4:
                makeAnimation(R.id.iv_quotes_param4, v);
                presenter.onmEtParam4QuoteClicked();
                break;

            case R.id.iv_delete_param4:
                makeAnimation(R.id.iv_delete_param4, v);
                presenter.onEtParam4DeleteClicked();
                break;

            case R.id.iv_quotes_param5:
                makeAnimation(R.id.iv_quotes_param5, v);
                presenter.onmEtParam5QuoteClicked();
                break;

            case R.id.iv_delete_param5:
                makeAnimation(R.id.iv_delete_param5, v);
                presenter.onEtParam5DeleteClicked();
                break;

            case R.id.iv_quotes_param6:
                makeAnimation(R.id.iv_quotes_param6, v);
                presenter.onmEtParam6QuoteClicked();
                break;

            case R.id.iv_delete_param6:
                makeAnimation(R.id.iv_delete_param6, v);
                presenter.onEtParam6DeleteClicked();
                break;

            case R.id.iv_quotes_param7:
                makeAnimation(R.id.iv_quotes_param7, v);
                presenter.onmEtParam7QuoteClicked();
                break;

            case R.id.iv_delete_param7:
                makeAnimation(R.id.iv_delete_param7, v);
                presenter.onEtParam7DeleteClicked();
                break;

            case R.id.iv_quotes_param8:
                makeAnimation(R.id.iv_quotes_param8, v);
                presenter.onmEtParam8QuoteClicked();
                break;

            case R.id.iv_delete_param8:
                makeAnimation(R.id.iv_delete_param8, v);
                presenter.onEtParam8DeleteClicked();
                break;

            default:
                break;
        }
    }

    @Override
    public void setVisibileQuotesParam8Iv() {
        mQuotesParam8Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam8Iv() {
        mDeleteParam8Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam8() {
        mEtParam8.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam8(String s) {
        mEtParam8.setHint(s);
    }

    @Override
    public void setTextEtParam8(String paramTexts) {
        mEtParam8.setText(paramTexts);
    }

    @Override
    public String getTextEtParam8() {
        return mEtParam8.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam7Iv() {
        mQuotesParam7Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam7Iv() {
        mDeleteParam7Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam7() {
        mEtParam7.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam7(String s) {
        mEtParam7.setHint(s);
    }

    @Override
    public void setTextEtParam7(String paramTexts) {
        mEtParam7.setText(paramTexts);
    }

    @Override
    public String getTextEtParam7() {
        return mEtParam7.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam6Iv() {
        mQuotesParam6Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam6Iv() {
        mDeleteParam6Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam6() {
        mEtParam6.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam6(String s) {
        mEtParam6.setHint(s);
    }

    @Override
    public void setTextEtParam6(String paramTexts) {
        mEtParam6.setText(paramTexts);
    }

    @Override
    public String getTextEtParam6() {
        return mEtParam6.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam5Iv() {
        mQuotesParam5Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam5Iv() {
        mDeleteParam5Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam5() {
        mEtParam5.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam5(String s) {
        mEtParam5.setHint(s);
    }

    @Override
    public void setTextEtParam5(String paramTexts) {
        mEtParam5.setText(paramTexts);
    }

    @Override
    public String getTextEtParam5() {
        return mEtParam5.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam4Iv() {
        mQuotesParam4Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam4Iv() {
        mDeleteParam4Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam4() {
        mEtParam4.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam4(String s) {
        mEtParam4.setHint(s);
    }

    @Override
    public void setTextEtParam4(String paramTexts) {
        mEtParam4.setText(paramTexts);
    }

    @Override
    public String getTextEtParam4() {
        return mEtParam4.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam3Iv() {
        mQuotesParam3Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam3Iv() {
        mDeleteParam3Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam3() {
        mEtParam3.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam3(String s) {
        mEtParam3.setHint(s);
    }

    @Override
    public void setTextEtParam3(String paramTexts) {
        mEtParam3.setText(paramTexts);
    }

    @Override
    public String getTextEtParam3() {
        return mEtParam3.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam2Iv() {
        mQuotesParam2Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam2Iv() {
        mDeleteParam2Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam2() {
        mEtParam2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam2(String s) {
        mEtParam2.setHint(s);
    }

    @Override
    public void setTextEtParam2(String paramTexts) {
        mEtParam2.setText(paramTexts);
    }

    @Override
    public String getTextEtParam2() {
        return mEtParam2.getText().toString();
    }

    @Override
    public void setVisibileQuotesParam1Iv() {
        mQuotesParam1Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibileDeleteParam1Iv() {
        mDeleteParam1Iv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleEtParam1() {
        mEtParam1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHintTomEtParam1(String s) {
        mEtParam1.setHint(s);
    }

    @Override
    public void setTextEtParam1(String paramTexts) {
        mEtParam1.setText(paramTexts);
    }

    @Override
    public String getTextEtParam1() {
        return mEtParam1.getText().toString();
    }

    @Override
    public void setVisibleEnterParamsTv() {
        mEnterParamsTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleLineParamsView() {
        mLineParamsView.setVisibility(View.VISIBLE);
    }


}
