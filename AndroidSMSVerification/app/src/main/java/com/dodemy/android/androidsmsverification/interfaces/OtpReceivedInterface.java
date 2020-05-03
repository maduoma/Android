package com.dodemy.android.androidsmsverification.interfaces;

public interface OtpReceivedInterface {
  void onOtpReceived(String otp);
  void onOtpTimeout();
}
