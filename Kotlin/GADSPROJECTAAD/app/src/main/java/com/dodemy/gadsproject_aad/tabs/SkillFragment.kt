package com.dodemy.gadsproject_aad.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.gadsproject_aad.DataViewModel
import com.dodemy.gadsproject_aad.R
import com.dodemy.gadsproject_aad.SkillIQAdapter
import com.dodemy.gadsproject_aad.model.SkillIQ
import java.util.*

class SkillFragment : Fragment() {
    private var dataViewModel: DataViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.skillIQRecyclerview)
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        dataViewModel!!.topSkill
        val adapter = SkillIQAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        dataViewModel!!.skillMutableData.observe(
            (Objects.requireNonNull(context) as LifecycleOwner?)!!,
            Observer<List<SkillIQ?>?> { postModels -> adapter.setList(postModels) })
    }
}
