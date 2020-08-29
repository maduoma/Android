package com.dodemy.mvp.View.DetailedActivity.Presenter;


import android.util.Log;

import com.dodemy.mvp.View.ConstantsOfApp;
import com.dodemy.mvp.View.DetailedActivity.View.IView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import bsh.EvalError;
import bsh.Interpreter;

public class Presenter implements IPresenter {

    private IView iView;

    public Presenter(IView iView) {
        this.iView = iView;
    }

    private String editQuoteClicked(String s) {
        if (!s.isEmpty()) {
            if (!s.substring(0, 1).equals("\"") & !s.substring(s.length() - 1).equals("\"")) {
                s = "\"" + s + "\"";
            } else {
                s = s.substring(1, s.length() - 1);
            }
        }
        return s;
    }

    private String[] getParametersFromStringToArray(String s) {
        String[] strings = new String[2];
        switch (s) {
            case ConstantsOfApp.INT_PARAM:
                strings[0] = "int";
                strings[1] = "1;";
                break;
            case ConstantsOfApp.DOUBLE_PARAM:
                strings[0] = ConstantsOfApp.DOUBLE_PARAM;
                strings[1] = "1.0;";
                break;
            case ConstantsOfApp.DOUBLE_LOWERCASE_PARAM:
                strings[0] = ConstantsOfApp.DOUBLE_LOWERCASE_PARAM;
                strings[1] = "1.0;";
                break;
            case ConstantsOfApp.DOUBLE_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.DOUBLE_ARRAY_PARAM;
                strings[1] = "{1.0, 2.0, 3.0};";
                break;
            case ConstantsOfApp.FLOAT_PARAM:
                strings[0] = ConstantsOfApp.FLOAT_PARAM;
                strings[1] = "1.0f;";
                break;
            case ConstantsOfApp.FLOAT_LOWERCASE_PARAM:
                strings[0] = ConstantsOfApp.FLOAT_LOWERCASE_PARAM;
                strings[1] = "1.0f;";
                break;
            case ConstantsOfApp.FLOAT_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.FLOAT_ARRAY_PARAM;
                strings[1] = "{1f, 2.0f, 3.0f};";
                break;
            case ConstantsOfApp.INT_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.INT_ARRAY_PARAM;
                strings[1] = "{1, 2, 3};";
                break;
            case ConstantsOfApp.INTEGER_PARAM:
                strings[0] = ConstantsOfApp.INTEGER_PARAM;
                strings[1] = "1;";
                break;
            case ConstantsOfApp.LONG_PARAM:
                strings[0] = ConstantsOfApp.LONG_PARAM;
                strings[1] = "1L;";
                break;
            case ConstantsOfApp.LONG_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.LONG_ARRAY_PARAM;
                strings[1] = "{1L, 2L, 3L};";
                break;
            case ConstantsOfApp.OBJECT_PARAM:
                strings[0] = ConstantsOfApp.OBJECT_PARAM;
                strings[1] = "\"a\";";
                break;
            case ConstantsOfApp.OBJECT_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.OBJECT_ARRAY_PARAM;
                strings[1] = "{\"any\", \"text\"};";
                break;
            case ConstantsOfApp.SHORT_PARAM:
                strings[0] = ConstantsOfApp.SHORT_PARAM;
                strings[1] = "1;";
                break;
            case ConstantsOfApp.SHORT_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.SHORT_ARRAY_PARAM;
                strings[1] = "{1, 2, 3};";
                break;
            case ConstantsOfApp.STRING_PARAM:
                strings[0] = ConstantsOfApp.STRING_PARAM;
                strings[1] = "\"any\";";
                break;
            case ConstantsOfApp.STRINGBUFFER_PARAM:
                strings[0] = ConstantsOfApp.STRINGBUFFER_PARAM;
                strings[1] = "new StringBuffer(\"anytext\");";
                break;
            case ConstantsOfApp.BOOLEAN_PARAM:
                strings[0] = ConstantsOfApp.BOOLEAN_PARAM;
                strings[1] = "true;";
                break;
            case ConstantsOfApp.BOOLEAN_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.BOOLEAN_ARRAY_PARAM;
                strings[1] = "{true,false};";
                break;
            case ConstantsOfApp.BYTE_PARAM:
                strings[0] = ConstantsOfApp.BYTE_PARAM;
                strings[1] = "1;";
                break;
            case ConstantsOfApp.BYTE_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.BYTE_ARRAY_PARAM;
                strings[1] = "{1, 2, 3};";
                break;
            case ConstantsOfApp.CALENDAR_PARAM:
                strings[0] = ConstantsOfApp.CALENDAR_PARAM;
                strings[1] = "Calendar.getInstance();";
                break;
            case ConstantsOfApp.CHAR_PARAM:
                strings[0] = ConstantsOfApp.CHAR_PARAM;
                strings[1] = "'a';";
                break;
            case ConstantsOfApp.CHAR_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.CHAR_ARRAY_PARAM;
                strings[1] = "{'a', 'n', 'y'};";
                break;
            case ConstantsOfApp.CHARACTER_PARAM:
                strings[0] = ConstantsOfApp.CHARACTER_PARAM;
                strings[1] = "'a';";
                break;
            case ConstantsOfApp.CHARSEQUENCE_PARAM:
                strings[0] = ConstantsOfApp.CHARSEQUENCE_PARAM;
                strings[1] = "\"abc\";";
                break;
            case ConstantsOfApp.CHARSET_PARAM:
                strings[0] = ConstantsOfApp.STRING_PARAM;
                strings[1] = "\"UTF-8\";";
                break;
            case ConstantsOfApp.U_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.STRING_ARRAY_PARAM;
                strings[1] = "{\"a\", \"n\", \"y\"};";
                break;
            case ConstantsOfApp.T_ARRAY_PARAM:
                strings[0] = ConstantsOfApp.STRING_ARRAY_PARAM;
                strings[1] = "{\"a\", \"n\", \"y\"};";
                break;
            case ConstantsOfApp.T_DOTS_PARAM:
                strings[0] = ConstantsOfApp.STRING_ARRAY_PARAM;
                strings[1] = "{\"a\", \"n\", \"y\"};";
                break;
            case ConstantsOfApp.OBJECT_DOTS_PARAM:
                strings[0] = ConstantsOfApp.STRING_ARRAY_PARAM;
                strings[1] = "{\"a\", \"n\", \"y\"};";
                break;
            case ConstantsOfApp.T_PARAM:
                strings[0] = ConstantsOfApp.STRING_PARAM;
                strings[1] = "\"a\"";
                break;
            case ConstantsOfApp.E_PARAM:
                strings[0] = ConstantsOfApp.STRING_PARAM;
                strings[1] = "\"a\"";
                break;
            case ConstantsOfApp.TIMEZONE_PARAM:
                strings[0] = ConstantsOfApp.TIMEZONE_PARAM;
                strings[1] = "TimeZone.getDefault();";
                break;
            case ConstantsOfApp.LOCALE_PARAM:
                strings[0] = ConstantsOfApp.LOCALE_PARAM;
                strings[1] = "new Locale(\"fr\");";
                break;
            case ConstantsOfApp.DATE_PARAM:
                strings[0] = ConstantsOfApp.DATE_PARAM;
                strings[1] = "new Date();";
                break;
            case ConstantsOfApp.COMPARATOR_SUPER_T_PARAM:
                strings[0] = ConstantsOfApp.OBJECT_PARAM;
                strings[1] = "null;";
                break;
            case ConstantsOfApp.COLLECTION_EXTENDS_E_PARAM:
                strings[0] = "ArrayList";
                strings[1] = " new ArrayList(){{" +
                        "add(\"A\");" +
                        "add(\"B\");" +
                        "add(\"C\");" +
                        "}};";

                ArrayList param = new ArrayList<String>();//Creating arraylist
                param.add("Ravi");//Adding object in arraylist
                param.add("Vijay");
                param.add("Ravi");
                param.add("Ajay");
                break;
            case ConstantsOfApp.VOID_PARAM:
                strings[0] = "123";
                strings[1] = "[\"a\", \"n\", \"y\"];";
                break;


        }

        return strings;
    }


    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void onSetInitialTextToEnterValue() {

        String getIntentObject = iView.getTextGetIntentObject();
        String s = "";
        switch (getIntentObject) {
            case ConstantsOfApp.STRING_OBJECT:
                s = "\"anytext\"";
                break;
            case ConstantsOfApp.INTEGER_OBJECT:
                s = "123";
                break;
            case ConstantsOfApp.ARRAYS_OBJECT://-?
                s = "\"anytext\"";
                break;
            case ConstantsOfApp.STRINGBUILDER_OBJECT://-?
                s = "\"anytext\"";
                break;
            case ConstantsOfApp.CALENDAR_OBJECT://-?
                s = "\"anytext\"";
                break;
            case ConstantsOfApp.SHORT_OBJECT:
                s = "123";
                break;
            case ConstantsOfApp.BYTE_OBJECT:
                s = "a";
                break;
            case ConstantsOfApp.BOOLEAN_OBJECT:
                s = "true";
                break;
            case ConstantsOfApp.LONG_OBJECT:
                s = "123";
                break;
            case ConstantsOfApp.DOUBLE_OBJECT:
                s = "123.0";
                break;
            case ConstantsOfApp.FLOAT_OBJECT:
                s = "123.0f";
                break;
            case ConstantsOfApp.CHARACTER_OBJECT:
                s = "\'a\'";
                break;
            case ConstantsOfApp.ARRAYLIST_OBJECT://-?
                s = "new ArrayList()";


                break;

        }
        iView.setTextEtEnterValue(s);

    }

    @Override
    public void setInitialTextToParams() {
        String[] splitParamsArrayOnlyType = iView.getSplitParamsArrayOnlyType();

        String paramValue;
        String[] strings;
        switch (splitParamsArrayOnlyType.length) {
            case 8:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[7]);
                paramValue = strings[0] + " param8 = " + strings[1];
                iView.setTextEtParam8(paramValue);

            case 7:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[6]);
                paramValue = strings[0] + " param7 = " + strings[1];
                iView.setTextEtParam7(paramValue);

            case 6:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[5]);
                paramValue = strings[0] + " param6 = " + strings[1];
                iView.setTextEtParam6(paramValue);

            case 5:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[4]);
                paramValue = strings[0] + " param5 = " + strings[1];
                iView.setTextEtParam5(paramValue);

            case 4:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[3]);
                paramValue = strings[0] + " param4 = " + strings[1];
                iView.setTextEtParam4(paramValue);

            case 3:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[2]);
                paramValue = strings[0] + " param3 = " + strings[1];
                iView.setTextEtParam3(paramValue);

            case 2:
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[1]);
                paramValue = strings[0] + " param2 = " + strings[1];
                iView.setTextEtParam2(paramValue);

            case 1:
                if (splitParamsArrayOnlyType[0].equals("")) {
                    break;
                }
                strings = getParametersFromStringToArray(splitParamsArrayOnlyType[0]);
                paramValue = strings[0] + " param1 = " + strings[1];
                iView.setTextEtParam1(paramValue);

            default:
                break;

        }
    }

    @Override
    public void setSplitParamsArray() {
        String getIntentMethod = iView.getTextGetIntentMethod();
        getIntentMethod = getIntentMethod.substring(getIntentMethod.indexOf("(") + 1, getIntentMethod.indexOf(")"));
        String[] splitParamsArray = getIntentMethod.split(", ");

        iView.setSplitParamsArray(splitParamsArray);
    }

    @Override
    public void setSplitParamsArrayOnlyType() {
        String[] splitParamsArray = iView.getSplitParamsArray();

        int i;

        switch (splitParamsArray.length) {
            case 8:
                i = splitParamsArray[7].indexOf(' ');
                splitParamsArray[7] = splitParamsArray[7].substring(0, i);

            case 7:
                i = splitParamsArray[6].indexOf(' ');
                splitParamsArray[6] = splitParamsArray[6].substring(0, i);

            case 6:
                i = splitParamsArray[5].indexOf(' ');
                splitParamsArray[5] = splitParamsArray[5].substring(0, i);

            case 5:
                i = splitParamsArray[4].indexOf(' ');
                splitParamsArray[4] = splitParamsArray[4].substring(0, i);

            case 4:
                i = splitParamsArray[3].indexOf(' ');
                splitParamsArray[3] = splitParamsArray[3].substring(0, i);

            case 3:

                i = splitParamsArray[2].indexOf(' ');
                splitParamsArray[2] = splitParamsArray[2].substring(0, i);

            case 2:
                i = splitParamsArray[1].indexOf(' ');
                splitParamsArray[1] = splitParamsArray[1].substring(0, i);

            case 1:
                if (splitParamsArray[0].equals("")) {
                    break;
                }
                i = splitParamsArray[0].indexOf(' ');
                splitParamsArray[0] = splitParamsArray[0].substring(0, i);

            default:
                break;

        }

        iView.setSplitParamsArrayOnlyType(splitParamsArray);
    }

    @Override
    public void onFormTextFromParams() {

        String mEtParam1 = iView.getTextEtParam1();
        String mEtParam2 = iView.getTextEtParam2();
        String mEtParam3 = iView.getTextEtParam3();
        String mEtParam4 = iView.getTextEtParam4();
        String mEtParam5 = iView.getTextEtParam5();
        String mEtParam6 = iView.getTextEtParam6();
        String mEtParam7 = iView.getTextEtParam7();
        String mEtParam8 = iView.getTextEtParam8();

        StringBuilder stringBuilder = new StringBuilder();

        //if param1 is not empty, then do: get text from param1 from EditText
        if (!mEtParam1.trim().equals("")) {
            stringBuilder.append(mEtParam1.trim() + "\n");
        }
        if (!mEtParam2.trim().equals("")) {
            stringBuilder.append(mEtParam2.trim() + "\n");
        }
        if (!mEtParam3.trim().equals("")) {
            stringBuilder.append(mEtParam3.trim() + "\n");
        }
        if (!mEtParam4.trim().equals("")) {
            stringBuilder.append(mEtParam4.trim() + "\n");
        }
        if (!mEtParam5.trim().equals("")) {
            stringBuilder.append(mEtParam5.trim() + "\n");
        }
        if (!mEtParam6.trim().equals("")) {
            stringBuilder.append(mEtParam6.trim() + "\n");
        }
        if (!mEtParam7.trim().equals("")) {
            stringBuilder.append(mEtParam7.trim() + "\n");
        }
        if (!mEtParam8.trim().equals("")) {
            stringBuilder.append(mEtParam8.trim() + "\n");
        }


        iView.setTextTempParamsText(stringBuilder.toString());
    }

    @Override
    public void onExecuteCodeFromTv() {
        String code = iView.getTextCodeEt();
        if (iView.getTextTvType().equals(ConstantsOfApp.VOID_PARAM)) {
            code = "void";
        } else {
            try {
                Interpreter i = new Interpreter();  // Construct an interpreter

                i.eval(code);

                code = "a = " + String.valueOf(i.get("a"));
            } catch (EvalError evalError) {

                code = evalError.toString();
            }
        }


        iView.setTextTvResult(code);
    }

    @Override
    public void onFormCodeAndSetToTv() {
        String codeToTv = "";

        String getIntentObject = iView.getTextGetIntentObject();

        String mEtEnterValue = iView.getTextEtEnterValue();
        String mTvType = iView.getTextTvType();
        String mTvMethod = iView.getTextTvMethod();

        String tempParamsText = iView.getTextTempParamsText();
        String[] tempStrings = tempParamsText.split(" =");
        String tempParamNames = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tempStrings.length - 1; i++) {
            stringBuilder.append(tempStrings[i].substring(tempStrings[i].length() - 6));
            stringBuilder.append(",");
        }
        if (stringBuilder.toString().length() - 1 >= 0) {
            tempParamNames = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
        }


        switch (getIntentObject) {
            case ConstantsOfApp.STRING_OBJECT:

                //Exception: check if String name is "charsetName", is so set it to "UTF-8"
                String getMethodParamTypeName = "";
                if (iView.getTextGetIntentMethod().contains(" ")) {
                    getMethodParamTypeName = iView.getTextGetIntentMethod().substring(iView.getTextGetIntentMethod().indexOf(" "), iView.getTextGetIntentMethod().length() - 1);
                    if (getMethodParamTypeName.equals("charsetName")) {
                        mEtEnterValue = "UTF-8";
                    }
                }

                //Exception: check if return type is "void"
                if (iView.getTextGetIntentType().equals(ConstantsOfApp.VOID_PARAM)) {
                    codeToTv = String.format(
                            "%s a = %s;\n\n" +
                                    "%s \n" +
                                    "a.%s%s);",
                            getIntentObject, mEtEnterValue.trim(),
                            tempParamsText,
                            mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                    );

                    String s = "anytext";

                    String param1 = "UTF-8";

                    try {
                        byte[] a = s.getBytes(param1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
                //else do as usual
                else {
                    codeToTv = String.format(
                            "%s s = %s;\n\n" +
                                    "%s \n" +
                                    "%s a = s.%s%s);",
                            getIntentObject, mEtEnterValue.trim(),
                            tempParamsText,
                            mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                    );
                }


                break;
            case ConstantsOfApp.INTEGER_OBJECT:


                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames

                );
                Log.i("autolog", "mTvType222: " + mTvType);
                break;
            case ConstantsOfApp.ARRAYS_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.STRINGBUILDER_OBJECT:

                codeToTv = String.format("%s s = new StringBuilder(%s);\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.CALENDAR_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.SHORT_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.BYTE_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.BOOLEAN_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.LONG_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.DOUBLE_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.FLOAT_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.CHARACTER_OBJECT:

                codeToTv = String.format("%s s = %s;\n\n" +
                                "%s \n" +
                                "%s a = s.%s%s);",
                        getIntentObject, mEtEnterValue.trim(),
                        tempParamsText,
                        mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames
                );
                break;
            case ConstantsOfApp.ARRAYLIST_OBJECT:

                String tempCode;

                if (mTvType.equals("void")) {
                    tempCode = String.format("import java.util.*;\n" +
                                    "%s s = %s;\n\n" +
                                    "%s \n" +
                                    "s.%s%s);",
                            getIntentObject, mEtEnterValue.trim(),
                            tempParamsText,
                            mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames);
                } else {

                    tempCode = String.format("import java.util.*;\n" +
                                    "%s s = %s;\n\n" +
                                    "%s \n" +
                                    "%s a = s.%s%s);",
                            getIntentObject, mEtEnterValue.trim(),
                            tempParamsText,
                            mTvType, mTvMethod.substring(0, mTvMethod.indexOf("(") + 1), tempParamNames


                    );
                }

                codeToTv = tempCode;
                break;

        }

        iView.setTextCodeEt(codeToTv);

    }


    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    @Override
    public void setEditTextChangedListener() {
        iView.setEditTextChangedListener();
    }

    @Override
    public void setDataToViewsReceivedFromPreviousActivity() {
        iView.setDataToViewsReceivedFromPreviousActivity();
    }

    @Override
    public void setDataFromPreviousActivity() {
        iView.setDataFromPreviousActivity();
    }

    @Override
    public void setViewsVisibility() {

        String[] strings = iView.getSplitParamsArray();
        switch (strings.length) {
            case 8:
                iView.setVisibileQuotesParam8Iv();
                iView.setVisibileDeleteParam8Iv();
                iView.setVisibleEtParam8();
                iView.setHintTomEtParam8(strings[7].trim());

            case 7:
                iView.setVisibileQuotesParam7Iv();
                iView.setVisibileDeleteParam7Iv();
                iView.setVisibleEtParam7();
                iView.setHintTomEtParam7(strings[6].trim());

            case 6:
                iView.setVisibileQuotesParam6Iv();
                iView.setVisibileDeleteParam6Iv();
                iView.setVisibleEtParam6();
                iView.setHintTomEtParam6(strings[5].trim());

            case 5:
                iView.setVisibileQuotesParam5Iv();
                iView.setVisibileDeleteParam5Iv();
                iView.setVisibleEtParam5();
                iView.setHintTomEtParam5(strings[4].trim());

            case 4:
                iView.setVisibileQuotesParam4Iv();
                iView.setVisibileDeleteParam4Iv();
                iView.setVisibleEtParam4();
                iView.setHintTomEtParam4(strings[3].trim());

            case 3:
                iView.setVisibileQuotesParam3Iv();
                iView.setVisibileDeleteParam3Iv();
                iView.setVisibleEtParam3();
                iView.setHintTomEtParam3(strings[2].trim());

            case 2:
                iView.setVisibileQuotesParam2Iv();
                iView.setVisibileDeleteParam2Iv();
                iView.setVisibleEtParam2();
                iView.setHintTomEtParam2(strings[1].trim());

            case 1:
                if (strings[0].equals("")) {
                    break;
                }
                iView.setVisibileQuotesParam1Iv();
                iView.setVisibileDeleteParam1Iv();
                iView.setVisibleEtParam1();
                iView.setHintTomEtParam1(strings[0]);
                iView.setVisibleEnterParamsTv();
                iView.setVisibleLineParamsView();

            default:
                break;

        }

    }

    @Override
    public void onEtEnterValueDeleteClicked() {
        iView.setTextEtEnterValue("");
    }

    @Override
    public void onEtParam1DeleteClicked() {
        iView.setTextEtParam1("");
    }

    @Override
    public void onEtParam2DeleteClicked() {
        iView.setTextEtParam2("");
    }

    @Override
    public void onEtParam3DeleteClicked() {
        iView.setTextEtParam3("");
    }

    @Override
    public void onEtParam4DeleteClicked() {
        iView.setTextEtParam4("");
    }

    @Override
    public void onEtParam5DeleteClicked() {
        iView.setTextEtParam5("");
    }

    @Override
    public void onEtParam6DeleteClicked() {
        iView.setTextEtParam6("");
    }

    @Override
    public void onEtParam7DeleteClicked() {
        iView.setTextEtParam7("");
    }

    @Override
    public void onEtParam8DeleteClicked() {
        iView.setTextEtParam8("");
    }

    @Override
    public void onEtEnterValueQuoteClicked() {
        String s = iView.getTextEtEnterValue();
        iView.setTextEtEnterValue(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam1QuoteClicked() {
        String s = iView.getTextEtParam1();
        iView.setTextEtParam1(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam2QuoteClicked() {
        String s = iView.getTextEtParam2();
        iView.setTextEtParam2(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam3QuoteClicked() {
        String s = iView.getTextEtParam3();
        iView.setTextEtParam3(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam4QuoteClicked() {

        String s = iView.getTextEtParam4();
        iView.setTextEtParam4(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam5QuoteClicked() {
        String s = iView.getTextEtParam5();
        iView.setTextEtParam5(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam6QuoteClicked() {
        String s = iView.getTextEtParam6();
        iView.setTextEtParam6(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam7QuoteClicked() {
        String s = iView.getTextEtParam7();
        iView.setTextEtParam7(editQuoteClicked(s));
    }

    @Override
    public void onmEtParam8QuoteClicked() {
        String s = iView.getTextEtParam8();
        iView.setTextEtParam8(editQuoteClicked(s));
    }

    //execute java code using BeenShell library


}




