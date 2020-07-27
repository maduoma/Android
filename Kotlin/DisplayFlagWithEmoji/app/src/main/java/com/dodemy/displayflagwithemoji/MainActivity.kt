package com.dodemy.displayflagwithemoji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_1.text = "NG".toFlagEmoji()
        text_2.text = "GB".toFlagEmoji()

        val constraint = Constraints.Builder().setRequiresCharging(true).build()
        val request = OneTimeWorkRequestBuilder<MyWork>()
            .setConstraints(constraint)
            .build()
        notifyButton.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
        }
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer { workInfo ->
                val status: String = workInfo.state.name
                Toast.makeText(this@MainActivity, status, Toast.LENGTH_LONG).show()
            })
    }

    private fun String.toFlagEmoji(): String {
        val countryCodeCaps = this.toUpperCase()
        val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }
}
