package com.dodemy.googlespreadsheetformwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SpreadsheetQuestions extends AppCompatActivity {

    private TextView nameInputField;
    private RadioGroup emergencyTypeSelect;
    private TextView emergencyInputField;
    private TextView mobileInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/0/d/e/")
                .build();
        final SpreadsheetQuestionsWebService spreadsheetWebService = retrofit.create(SpreadsheetQuestionsWebService.class);

        nameInputField = (TextView) findViewById(R.id.input_name);
        emergencyTypeSelect = (RadioGroup) findViewById(R.id.emergency_type);
        emergencyInputField = (TextView) findViewById(R.id.input_emergency);
        mobileInputField = (TextView) findViewById(R.id.input_mobile);
        findViewById(R.id.questions_submit_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameInput = nameInputField.getText().toString();
                        String emergencyType = String.valueOf(emergencyTypeSelect.getCheckedRadioButtonId());
                        if (Integer.parseInt(emergencyType) == 2131230866) {
                            emergencyType = "Fire";
                        } else if (Integer.parseInt(emergencyType) == 2131230864){
                            emergencyType = "Accident";
                        } else if (Integer.parseInt(emergencyType) == 2131230869){
                            emergencyType = "Robbery";
                        } else if (Integer.parseInt(emergencyType) == 2131230865){
                            emergencyType = "Fight";
                        } else if (Integer.parseInt(emergencyType) == 2131230867){
                            emergencyType = "Food";
                        }else if (Integer.parseInt(emergencyType) == 2131230868){
                            emergencyType = "Others";
                        } else {
                            //Integer.parseInt(emergencyType) == -1
                            emergencyType = String.valueOf(false);
                        }
                        String emergencyInput = emergencyInputField.getText().toString();
                        String mobileInput = mobileInputField.getText().toString();
                        Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(nameInput, emergencyType, emergencyInput, mobileInput);
                        completeQuestionnaireCall.enqueue(callCallback);
                    }
                }
        );
    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Toast.makeText(getApplicationContext(), "Submitted "+response.message(), Toast.LENGTH_LONG).show();
            Log.d("XXX", "Submitted. " + response);

        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Failed "+t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("XXX", "Failed", t);

        }
    };

}