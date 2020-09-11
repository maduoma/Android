package com.dodemy.gadsprojectwithretrofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmission extends AppCompatActivity {

    private TextInputLayout mFirstName;
    private TextInputLayout mLastName;
    private TextInputLayout mEmail;
    private TextInputLayout mGithubLink;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        setSupportActionBar(findViewById(R.id.submission_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.project_submission));

        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email_address);
        mGithubLink = findViewById(R.id.github_link);
        mProgressBar = findViewById(R.id.submit_progressBar);
    }

    public void submitDetails(String firstName, String lastName, String email, String link) {
        mProgressBar.setVisibility(View.VISIBLE);
        Utilities
                .GadsApiUtility
                .submitProject(firstName, lastName, email, link)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        mProgressBar.setVisibility(View.GONE);
                        if (!response.isSuccessful()) {
                            new AlertDialog.Builder(ProjectSubmission.this)
                                    .setTitle("Failed to Submit Project")
                                    .setMessage(response.message() + "\n" + response.code())
                                    .create()
                                    .show()
                            ;
                        }

                        new AlertDialog.Builder(ProjectSubmission.this)
                                .setTitle("Success ")
                                .setMessage("Project Submitted Successfully")
                                .create()
                                .show()
                        ;
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        mProgressBar.setVisibility(View.GONE);
                        new AlertDialog.Builder(ProjectSubmission.this)
                                .setTitle("Failed to Submit Project")
                                .setMessage(t.getMessage())
                                .create()
                                .show()
                        ;
                    }
                })
        ;
    }

    public void submitDetails(View view) {
        String firstName = mFirstName.getEditText().getText().toString().trim();
        String lastName = mLastName.getEditText().getText().toString().trim();
        String email = mEmail.getEditText().getText().toString().trim();
        String link = mGithubLink.getEditText().getText().toString().trim();

        if (validateInputs(firstName, mFirstName) && validateInputs(lastName, mLastName)
                && validateInputs(email, mEmail) && validateInputs(link, mGithubLink)) {

            new AlertDialog.Builder(this)
                    .setTitle("Are you Sure ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            submitDetails(firstName, lastName, email, link);
                        }
                    })
                    .create()
                    .show()
            ;
        }
    }

    private boolean validateInputs(String value, TextInputLayout view) {
        if (value.isEmpty()) {
            view.setError("Field must not be empty");
            view.requestFocus();
            return false;
        } else {
            view.setError(null);
            return true;
        }
    }
}