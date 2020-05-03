package com.dodemy.android.androidsmsverification;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dodemy.android.androidsmsverification.interfaces.OtpReceivedInterface;
import com.dodemy.android.androidsmsverification.receiver.SmsBroadcastReceiver;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        OtpReceivedInterface, GoogleApiClient.OnConnectionFailedListener {
  GoogleApiClient mGoogleApiClient;
  SmsBroadcastReceiver mSmsBroadcastReceiver;
  private int RESOLVE_HINT = 2;
  EditText inputMobileNumber, inputOtp;
  Button btnGetOtp, btnVerifyOtp;
  ConstraintLayout layoutInput, layoutVerify;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
    // init broadcast receiver
    mSmsBroadcastReceiver = new SmsBroadcastReceiver();

    //set google api client for hint request
    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .enableAutoManage(this, this)
        .addApi(Auth.CREDENTIALS_API)
        .build();

    mSmsBroadcastReceiver.setOnOtpListeners(this);
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
    getApplicationContext().registerReceiver(mSmsBroadcastReceiver, intentFilter);

    // get mobile number from phone
    getHintPhoneNumber();
    btnGetOtp.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // Call server API for requesting OTP and when you got success start
        // SMS Listener for listing auto read message lsitner
        startSMSListener();
      }
    });
  }

  private void initViews() {
    inputMobileNumber = findViewById(R.id.editTextInputMobile);
    inputOtp = findViewById(R.id.editTextOTP);
    btnGetOtp = findViewById(R.id.buttonGetOTP);
    btnVerifyOtp = findViewById(R.id.buttonVerify);
    layoutInput = findViewById(R.id.getOTPLayout);
    layoutVerify = findViewById(R.id.verifyOTPLayout);
  }

  @Override public void onConnected(@Nullable Bundle bundle) {

  }

  @Override public void onConnectionSuspended(int i) {

  }

  @Override public void onOtpReceived(String otp) {
    Toast.makeText(this, "Otp Received " + otp, Toast.LENGTH_LONG).show();
    inputOtp.setText(otp);
  }

  @Override public void onOtpTimeout() {
    Toast.makeText(this, "Time out, please resend", Toast.LENGTH_LONG).show();
  }

  @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  public void startSMSListener() {
    SmsRetrieverClient mClient = SmsRetriever.getClient(this);
    Task<Void> mTask = mClient.startSmsRetriever();
    mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override public void onSuccess(Void aVoid) {
        layoutInput.setVisibility(View.GONE);
        layoutVerify.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "SMS Retriever starts", Toast.LENGTH_LONG).show();
      }
    });
    mTask.addOnFailureListener(new OnFailureListener() {
      @Override public void onFailure(@NonNull Exception e) {
        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
      }
    });
  }

  public void getHintPhoneNumber() {
    HintRequest hintRequest =
        new HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build();
    PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
    try {
      startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);
    } catch (IntentSender.SendIntentException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    //Result if we want hint number
    if (requestCode == RESOLVE_HINT) {
      if (resultCode == Activity.RESULT_OK) {
        if (data != null) {
          Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
          // credential.getId();  <-- will need to process phone number string
          inputMobileNumber.setText(credential.getId());
        }

      }
    }
  }
}
