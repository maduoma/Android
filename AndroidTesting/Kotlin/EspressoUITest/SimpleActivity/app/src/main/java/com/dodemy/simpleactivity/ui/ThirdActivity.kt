package com.dodemy.simpleactivity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.simpleactivity.R
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        backToSecondActBtnTitle.setOnClickListener {
            onBackPressed()
        }
    }
}