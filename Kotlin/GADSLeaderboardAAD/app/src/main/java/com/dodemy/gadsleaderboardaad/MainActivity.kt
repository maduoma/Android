package com.dodemy.gadsleaderboardaad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController

import com.dodemy.gadsleaderboardaad.databinding.ActivityMainBinding
import com.dodemy.gadsleaderboardaad.ui.LeaderBoardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
        val navController = findNavController(R.id.nav_host_fragment)
        binding.submitButton.setOnClickListener {
            navController.navigate(LeaderBoardFragmentDirections.actionLeaderBoardFragmentToSubmissionFragment())
        }
    }


    fun setUpNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.submissionFragment -> binding.toolbar.visibility = View.GONE
                else -> binding.toolbar.visibility = View.VISIBLE
            }

        }
    }

    fun showLoading(message: String) {
        binding.loadingLayoutContainer.progressMessage.text = message
        binding.loadingLayoutContainer.loadingLayoutContainer.visibility = View.VISIBLE
    }

    fun dismissLoading() {
        binding.loadingLayoutContainer.loadingLayoutContainer.visibility = View.INVISIBLE
    }
}