package com.dodemy.android.androidsmsverification;

import android.app.Application;

import com.dodemy.android.androidsmsverification.helper.AppSignatureHelper;

public class SmsVerificationApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    AppSignatureHelper appSignatureHelper = new AppSignatureHelper(this);
    appSignatureHelper.getAppSignatures();
  }
}
