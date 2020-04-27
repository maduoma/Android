package com.dodemy.android.roomwithjava.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.android.roomwithjava.R;
import com.dodemy.android.roomwithjava.db.AppDatabase;
import com.dodemy.android.roomwithjava.model.User;
import com.dodemy.android.roomwithjava.ui.adapter.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements UserAdapter.Callback {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    AppDatabase database;

    UserAdapter mUserAdapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        database=AppDatabase.getDatabaseInstance(this);
        setUp();
    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mUserAdapter = new UserAdapter(new ArrayList<User>());
        mUserAdapter.setCallback(this);
        prepareDemoContent();
        mRecyclerView.setAdapter(mUserAdapter);

    }

    private void prepareDemoContent() {
        User mUser1 = new User("Ramesh", "Chandra", "ramesh@gmail.com", null, null, new Date(), new Date());
        database.userDao().insertUser(mUser1);

        User mUser2 = new User("Sachin", "Kalal", "chnada@gmail.com", null, null, new Date(), new Date());
        database.userDao().insertUser(mUser2);

        User mUser3 = new User("Amit", "Kumar", "arun@gmail.com", null, null, new Date(), new Date());
        database.userDao().insertUser(mUser3);

        User mUser4 = new User("Kapil", "sharma", "kapil@gmail.com", null, null, new Date(), new Date());
        database.userDao().insertUser(mUser4);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserAdapter.addItems(database.userDao().getAll());
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        AddUserActivity.startActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    @Override
    public void onDeleteClick(User mUser) {
        database.userDao().delete(mUser);
        mUserAdapter.addItems(database.userDao().getAll());
    }
}