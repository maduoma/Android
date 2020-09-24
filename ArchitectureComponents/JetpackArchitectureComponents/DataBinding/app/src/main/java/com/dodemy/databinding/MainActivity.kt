package com.dodemy.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dodemy.databinding.databinding.ActivityMainBinding

/**
 * @author Maduabughichi Achilefu
 * Layout and Binding expressions
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Replaces & sets setContentView(R.layout.activity_main) with Data Binding
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Binds views and widgets using Data Binding
        binding.name1.text = getString(R.string.name1)
        binding.email1.text = getString(R.string.email1)

        //Binds data objects with views
        binding.contact = Contact("Maduabughichi Achilefu2", "email1@gmail.com2")

        //Event handling with Data Binding
        binding.handler = EventHandler(this)

    }
}