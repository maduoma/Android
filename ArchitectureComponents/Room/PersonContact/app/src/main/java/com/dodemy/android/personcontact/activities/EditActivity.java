package com.dodemy.android.personcontact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dodemy.android.personcontact.R;
import com.dodemy.android.personcontact.constants.Constants;
import com.dodemy.android.personcontact.database.AppDatabase;
import com.dodemy.android.personcontact.database.AppExecutors;
import com.dodemy.android.personcontact.model.Person;


public class EditActivity extends AppCompatActivity {
    EditText name, email, pincode, city, phoneNumber;
    Button button;
    int mPersonId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            button.setText(R.string.update);
            //button.setText(R.string.update);

            mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = mDb.personDao().loadPersonById(mPersonId);
                    populateUI(person);
                }
            });


        }

    }

    private void populateUI(Person person) {

        if (person == null) {
            return;
        }

        name.setText(person.getName());
        email.setText(person.getEmail());
        phoneNumber.setText(person.getNumber());
        pincode.setText(person.getPincode());
        city.setText(person.getCity());
    }

    private void initViews() {
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        pincode = findViewById(R.id.edit_pincode);
        city = findViewById(R.id.edit_city);
        phoneNumber = findViewById(R.id.edit_number);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final Person person = new Person(
                name.getText().toString(),
                email.getText().toString(),
                phoneNumber.getText().toString(),
                pincode.getText().toString(),
                city.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Person_Id)) {
                    mDb.personDao().insertPerson(person);
                } else {
                    person.setId(mPersonId);
                    mDb.personDao().updatePerson(person);
                }
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
