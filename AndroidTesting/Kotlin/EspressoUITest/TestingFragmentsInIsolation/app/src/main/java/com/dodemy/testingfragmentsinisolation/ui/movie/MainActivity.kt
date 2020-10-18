package com.dodemy.testingfragmentsinisolation.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.testingfragmentsinisolation.factory.MovieFragmentFactory
import com.dodemy.testingfragmentsinisolation.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MovieFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            val movieId = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .commit()
        }
    }

}







