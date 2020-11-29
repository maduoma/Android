package com.dodemy.room_bakingapp.ui.main;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.room_bakingapp.R;
import com.dodemy.room_bakingapp.data.FoodRepository;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;
import com.dodemy.room_bakingapp.ui.detail.RecipeDetailActivity;
import com.dodemy.room_bakingapp.utils.Constant;
import com.dodemy.room_bakingapp.utils.InjectorUtil;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private RecipeListAdapter adapter;
    private boolean tabletSize;
    private MainActivityViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabletSize = getResources().getBoolean(R.bool.isTablet);

        initRecyclerView();

        viewModelSetUp();
    }

    private void initRecyclerView() {
        // progress init
        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recipeListView = findViewById(R.id.list_recipe);
        adapter = new RecipeListAdapter(this, new RecipeListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(RecipeResponse recipeResponse) {
                Timber.d("item clicked " + recipeResponse.getName());
                startDetailActivity(recipeResponse);
            }
        });
        recipeListView.setAdapter(adapter);
//        int recyclerViewSpanCount = getResources().getConfiguration().orientation ==
//                Configuration.ORIENTATION_PORTRAIT ? 3 : 5;

        if (tabletSize) {
            GridLayoutManager manager = new GridLayoutManager(this, 2,
                    GridLayoutManager.VERTICAL, false);
            recipeListView.setLayoutManager(manager);
            recipeListView.addItemDecoration(new SpacesItemDecoration(4));
        } else {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recipeListView.setLayoutManager(manager);
        }
    }

    private void startDetailActivity(RecipeResponse recipeResponse) {
        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
        intent.putExtra(Constant.EXTRA_KEY, recipeResponse);
        startActivity(intent);
    }

    private void viewModelSetUp() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getRecipeLiveData().observe(this, new Observer<List<RecipeResponse>>() {
            @Override
            public void onChanged(@Nullable List<RecipeResponse> recipeResponses) {
                if (recipeResponses != null) {
                    adapter.setList(recipeResponses);
                    progressBar.setVisibility(View.GONE);
                    Timber.d(recipeResponses.toString());
                }
            }
        });
    }

}
