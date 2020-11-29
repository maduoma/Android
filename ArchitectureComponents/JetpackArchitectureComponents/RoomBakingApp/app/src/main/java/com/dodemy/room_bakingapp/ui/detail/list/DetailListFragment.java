package com.dodemy.room_bakingapp.ui.detail.list;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.room_bakingapp.R;
import com.dodemy.room_bakingapp.data.FoodRepository;
import com.dodemy.room_bakingapp.data.db.entities.Ingredient;
import com.dodemy.room_bakingapp.data.db.entities.RecipeResponse;
import com.dodemy.room_bakingapp.data.db.entities.Step;
import com.dodemy.room_bakingapp.ui.widget.IngredientsWidget;
import com.dodemy.room_bakingapp.utils.Constant;
import com.dodemy.room_bakingapp.utils.InjectorUtil;
import com.dodemy.room_bakingapp.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DetailListFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecipeResponse recipeResponse;
    @BindView(R.id.rv_recipe_steps)
    RecyclerView stepListView;
    @BindView(R.id.recipe_details_ingredients)
    TextView ingredientsView;
    private Context context;
    private RecipeStepAdapter adapter;

    private OnDetailListListener mListener;

    public DetailListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1
     * @return A new instance of fragment DetailListFragment.
     */
    public static DetailListFragment newInstance(RecipeResponse param1) {
        DetailListFragment fragment = new DetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_KEY, param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipeResponse = getArguments().getParcelable(Constant.EXTRA_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        context = getContext();
        // set ingredients
        createAndSetIngredientList(recipeResponse.getIngredients());
        // set recycler view for steps
        initRecyclerView();

    }

    private void initRecyclerView() {
        adapter = new RecipeStepAdapter(context, new RecipeStepAdapter.StepClickListener() {
            @Override
            public void onItemClick(Step step) {
                Timber.d(step.getShortDescription());
                mListener.onFragmentInteraction(step);
            }
        });
        stepListView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        stepListView.setLayoutManager(manager);
        stepListView.addItemDecoration(new DividerItemDecoration(context, manager.getOrientation()));
        adapter.setList(recipeResponse.getSteps());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailListListener) {
            mListener = (OnDetailListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDetailListListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnDetailListListener {
        void onFragmentInteraction(Step step);
    }

    private void createAndSetIngredientList(List<Ingredient> ingredients) {

        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.label_ingredient_list));

        for (Ingredient ing : ingredients
                ) {
            sb.append("\n");
            sb.append(StringUtils.formatIngredient(context, ing.getIngredient(), ing.getQuantity(), ing.getMeasure()));

        }
        ingredientsView.setText(sb.toString());
        updateWidgetMethod(sb.toString());
    }

    private void updateWidgetMethod(String sb) {
        FoodRepository repository = InjectorUtil.provideRepository(context);
        repository.setCurrentRecipeIngredient(sb);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        if (getActivity() != null) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.ingredients_widget);
            ComponentName componentName = new ComponentName(getActivity(), IngredientsWidget.class);
            remoteViews.setTextViewText(R.id.ingredient_text, sb);
            manager.updateAppWidget(componentName, remoteViews);
        }
    }


}

