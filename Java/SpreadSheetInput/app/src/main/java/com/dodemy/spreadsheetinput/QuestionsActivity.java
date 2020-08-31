package com.dodemy.spreadsheetinput;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionsActivity extends AppCompatActivity {

    private TextView nameInputField;
    private CheckBox catQuestionInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/u/0/d/e/")
            .build();
        final QuestionsSpreadsheetWebService spreadsheetWebService = retrofit.create(QuestionsSpreadsheetWebService.class);

        nameInputField = (TextView) findViewById(R.id.question_name_input);
        catQuestionInputField = (CheckBox) findViewById(R.id.question_cats_input);
        findViewById(R.id.questions_submit_button).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameInput = nameInputField.getText().toString();
                    String catQuestionInput = String.valueOf(catQuestionInputField.isChecked());
                    Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(nameInput, catQuestionInput);
                    completeQuestionnaireCall.enqueue(callCallback);
                }
            }
        );
    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(response.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Submitted "+response.message(), Toast.LENGTH_SHORT).show();
                Log.d("XXX", "Submitted. " + response);
            }


        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Submitted "+t.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("XXX", "Failed", t);
        }
    };
}
