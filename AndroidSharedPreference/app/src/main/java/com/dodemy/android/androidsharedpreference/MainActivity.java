package com.dodemy.android.androidsharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFERENCE = "myPref";
    public static final String PERSON_NAME = "nameKey";
    public static final String PHONE_NUMBER = "phoneKey";
    public static final String PERSON_EMAIL = "emailKey";
    EditText personName, phoneNumber, emailAddress;
    Button saveButton;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personName = findViewById(R.id.personName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailAddress = findViewById(R.id.emailAddress);
        saveButton = findViewById(R.id.saveButton);
        sharedpreferences = getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myName = personName.getText().toString();
                String myNum = phoneNumber.getText().toString();
                String myEmail = emailAddress.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(PERSON_NAME, myName);
                editor.putString(PHONE_NUMBER, myNum);
                editor.putString(PERSON_EMAIL, myEmail);
                editor.apply();
                Toast.makeText(MainActivity.this, "Thanks", Toast.LENGTH_LONG).show();
            }
        });
    }
}