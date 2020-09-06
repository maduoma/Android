package com.dodemy.gadsproject_aad.tabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dodemy.gadsproject_aad.DataViewModel;
import com.dodemy.gadsproject_aad.R;
import com.dodemy.gadsproject_aad.SkillIQAdapter;
import com.dodemy.gadsproject_aad.model.SkillIQ;

import java.util.List;
import java.util.Objects;


public class SkillFragment extends Fragment {
    DataViewModel dataViewModel;

    public SkillFragment() {
        // Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.skillIQRecyclerview);
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.getTopSkill();

        final SkillIQAdapter adapter = new SkillIQAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        dataViewModel.skillMutableData.observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<SkillIQ>>() {
            @Override
            public void onChanged(List<SkillIQ> postModels) {
                adapter.setList(postModels);
            }
        });
    }
}
