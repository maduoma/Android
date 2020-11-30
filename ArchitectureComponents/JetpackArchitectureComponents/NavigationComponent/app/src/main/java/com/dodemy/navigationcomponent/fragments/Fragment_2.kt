package com.dodemy.navigationcomponent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dodemy.navigationcomponent.R
import kotlinx.android.synthetic.main.fragment_2.*


class Fragment_2 : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        view?.let { super.onViewCreated(it, savedInstanceState) }
        btn_fragment_2.setOnClickListener {
            val navController: NavController = Navigation.findNavController(it)
            navController.navigate(Fragment_2Directions.actionFragment2ToFragment3())
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }
}
