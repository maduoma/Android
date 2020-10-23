package com.dodemy.espressotesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextName = (EditText)findViewById(R.id.editTextName);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FruitListActivity.class));
            }
        });
    }

    public void setDefaultText(View view) {
        editTextName.setText("");
    }

/*
    onView also has an overloaded version which can accept powerful hamcrest Matcher methods to go one step beyond and perform specific operations like:
1.  AllOf : To perform multiple operations together.

    onView(AllOf.allOf(withId(R.id.editTextName),withId(R.id.editTextSameName))).check(matches(withText("Pavneet")));
    java
2.  StringContains : To match a part of the input string in the targeted view text.

    onView(withId(R.id.editTextName)).check(matches(withText(Matchers.containsString("Pavneet"))));
    java
3.   StringStartsWith : To find the view with prefix.
            1
    onView(withText(startsWith("prefix"))).perform(click());
*/
}