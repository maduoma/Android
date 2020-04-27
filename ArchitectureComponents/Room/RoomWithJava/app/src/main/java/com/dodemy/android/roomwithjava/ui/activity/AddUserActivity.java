package com.dodemy.android.roomwithjava.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dodemy.android.roomwithjava.db.AppDatabase;
import com.dodemy.android.roomwithjava.R;
import com.dodemy.android.roomwithjava.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserActivity extends AppCompatActivity {

    @BindView(R.id.textFirstName)
    TextInputEditText textFirstName;
    @BindView(R.id.textLastName)
    TextInputEditText textLastName;
    @BindView(R.id.textEmail)
    TextInputEditText textEmail;
    @BindView(R.id.textPhoneNo)
    TextInputEditText textPhoneNo;
    @BindView(R.id.textAddress)
    TextInputEditText textAddress;
    @BindView(R.id.buttonAddUser)
    Button buttonAddUser;
    @BindView(R.id.textInputLayoutFirstName)
    TextInputLayout textInputLayoutFirstName;
    @BindView(R.id.textInputLayoutEmail)
    TextInputLayout textInputLayoutEmail;

    AppDatabase database;

    public static void startActivity(Context mContext) {
        Intent mIntent = new Intent(mContext, AddUserActivity.class);
        mContext.startActivity(mIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ButterKnife.bind(this);
        database = AppDatabase.getDatabaseInstance(this);

    }

    @OnClick(R.id.buttonAddUser)
    public void onViewClicked() {
        if (Objects.requireNonNull(textFirstName.getText()).toString().trim().isEmpty()) {
            textInputLayoutFirstName.setError(getString(R.string.error_msg_firstname));
            return;
        }

        if (Objects.requireNonNull(textEmail.getText()).toString().trim().isEmpty()) {
            textInputLayoutEmail.setError(getString(R.string.error_msg_email));
            return;
        }
        User mUser = new User(textFirstName.getText().toString(), Objects.requireNonNull(textLastName.getText()).toString(), textEmail.getText().toString(), Objects.requireNonNull(textPhoneNo.getText()).toString(), Objects.requireNonNull(textAddress.getText()).toString(), new Date(), new Date());
        database.userDao().insertUser(mUser);
        finish();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();

    }
}