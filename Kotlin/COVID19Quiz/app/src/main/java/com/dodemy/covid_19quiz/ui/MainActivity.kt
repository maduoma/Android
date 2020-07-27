package com.dodemy.covid_19quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.dodemy.covid_19quiz.R

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.homeFragment -> hideToolbar()
                R.id.quizFragment -> hideToolbar()
                R.id.readMoreFragment -> showToolbar()
            }
        }
    }

    private fun hideToolbar() {
        supportActionBar?.hide()
    }

    private fun showToolbar() {
        supportActionBar?.show()
    }
}
