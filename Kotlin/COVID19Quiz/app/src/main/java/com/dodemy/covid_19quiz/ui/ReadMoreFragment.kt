package com.dodemy.covid_19quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dodemy.covid_19quiz.R
import com.dodemy.covid_19quiz.databinding.FragmentReadMoreBinding


class ReadMoreFragment : Fragment() {

    lateinit var binding: FragmentReadMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReadMoreBinding.inflate(inflater, container, false)
        return binding.root
    }
}
