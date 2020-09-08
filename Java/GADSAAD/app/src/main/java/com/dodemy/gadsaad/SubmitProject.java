package com.dodemy.gadsaad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SubmitProject extends AppCompatActivity {
    EditText firstName, lastName, projectLink, email;
    Button submit;
    ProgressDialog progressDialog;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        email = findViewById(R.id.email);
        firstName = findViewById(R.id.edtName);
        lastName = findViewById(R.id.lastName);
        queue = Volley.newRequestQueue(getApplicationContext());
        projectLink = findViewById(R.id.link);
        submit = findViewById(R.id.submitBtn);
        submit.setOnClickListener(v -> openDialog());

//        //Building Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://docs.google.com/forms/d/e/")
//                .build();
//        final FormWebService formWebService = retrofit.create(FormWebService.class);
//        Call<Void> completeCall = formWebService.completeWebService(email, firstName, lastName, projectLink);
//        completeCall.enqueue(callCallback);
    }
//
//    private final Callback<Void> callCallback = new Callback<Void>() {
//        @Override
//        public void onResponse(Call<Void> call, Response<Void> response) {
//            Toast.makeText(getApplicationContext(), "Submitted " + response.message(), Toast.LENGTH_LONG).show();
//            Log.d("XXX", "Submitted. " + response);
//
//        }
//
//        @Override
//        public void onFailure(Call<Void> call, Throwable t) {
//            Toast.makeText(getApplicationContext(), "Failed " + t.getMessage(), Toast.LENGTH_LONG).show();
//            Log.e("XXX", "Failed", t);
//
//        }
//    };

    //Posting data with Volley library
    public void postData(final String name, final String email, final String link, final String lastName) {
        progressDialog.show();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.url,
                response -> {
                    Log.d("TAG", "Response: " + response);
                    if (response.length() > 0) {
                        successDialog();
                        SubmitProject.this.email.setText(null);
                        firstName.setText(null);
                        SubmitProject.this.lastName.setText(null);
                        projectLink.setText(null);
                    }
                    progressDialog.dismiss();
                }, error -> {
            progressDialog.dismiss();
            failedDialog();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.name, name);
                params.put(Constants.email, email);
                params.put(Constants.lastName, lastName);
                params.put(Constants.projectLink, link);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.confirm);
        Button yesDialog = dialog.findViewById(R.id.yes);
        Button noDialog = dialog.findViewById(R.id.closeImg);
        noDialog.setOnClickListener(v -> dialog.dismiss());
        yesDialog.setOnClickListener(v -> {
            postData(firstName.getText().toString().trim(), email.getText().toString().trim(), projectLink.getText().toString().trim(), lastName.getText().toString().trim());
            dialog.dismiss();
        });
        dialog.show();

    }

    private void successDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sucess_popup);
        Button closeDialog = dialog.findViewById(R.id.successImg);
        closeDialog.setOnClickListener(v -> dialog.dismiss());
        dialog.show();

    }

    private void failedDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.failure_popup);
        Button closeDialog = dialog.findViewById(R.id.failedImg);
        closeDialog.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}