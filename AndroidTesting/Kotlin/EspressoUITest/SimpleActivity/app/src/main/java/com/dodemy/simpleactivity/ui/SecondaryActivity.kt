package com.dodemy.simpleactivity.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.simpleactivity.R
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        button_back.setOnClickListener {
            onBackPressed()
        }

        goToThirdActivity.setOnClickListener {
            val thirdActivity = Intent(this, ThirdActivity::class.java)
            startActivity(thirdActivity)
        }
    }
}
