package com.dodemy.android.personcontact.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.android.personcontact.R;
import com.dodemy.android.personcontact.adaptors.PersonAdaptor;
import com.dodemy.android.personcontact.database.AppDatabase;
import com.dodemy.android.personcontact.database.AppExecutors;
import com.dodemy.android.personcontact.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    private PersonAdaptor mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.addFAB);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new PersonAdaptor(this);
        recyclerView.setAdapter(mAdapter);
        mDb = AppDatabase.getInstance(getApplicationContext());
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Person> tasks = mAdapter.getTasks();
                        mDb.personDao().delete(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Person> persons = mDb.personDao().loadAllPersons();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.setTasks(persons);
                    }
                });
            }
        });


    }
}
