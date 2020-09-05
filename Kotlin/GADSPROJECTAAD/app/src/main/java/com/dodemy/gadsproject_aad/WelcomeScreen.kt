package com.dodemy.gadsproject_aad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat


class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        val btnCreate = findViewById<Button>(R.id.btn)
        btnCreate.setOnClickListener { v -> Companion.presentActivity(this, v) }
    }

    companion object {
        fun presentActivity(welcomeScreen: WelcomeScreen, view: View) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(welcomeScreen, view, "transition")
            //val revealX = (view.x + view.width / 2).toInt()
            //val revealY = (view.y + view.height / 2).toInt()
            val intent = Intent(welcomeScreen, MainActivity::class.java)
            //intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
            //intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)
            ActivityCompat.startActivity(welcomeScreen, intent, options.toBundle())
        }
    }
}
