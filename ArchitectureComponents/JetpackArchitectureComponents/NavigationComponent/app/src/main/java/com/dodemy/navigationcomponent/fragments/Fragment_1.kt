package com.dodemy.navigationcomponent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dodemy.navigationcomponent.R
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment_1 : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        btn_fragment_1.setOnClickListener {
            //1
            val navController: NavController = Navigation.findNavController(it)
            //2
            val action = Fragment_1Directions.actionFragment1ToFragment2()
            //3
            navController.navigate(action)

        }



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)


    }

}
