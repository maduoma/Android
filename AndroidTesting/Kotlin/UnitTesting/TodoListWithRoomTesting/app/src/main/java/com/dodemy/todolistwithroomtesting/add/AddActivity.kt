package com.dodemy.todolistwithroomtesting.add

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dodemy.todolistwithroomtesting.R
import com.google.android.material.snackbar.Snackbar
import com.dodemy.todolistwithroomtesting.obtainViewModel
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addViewModel = obtainViewModel(AddViewModel::class.java)

        due.date = System.currentTimeMillis()
        due.setOnDateChangeListener { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            addViewModel.todo.dueDate = calendar.timeInMillis
            clearDue.visibility = View.VISIBLE
        }

        save.setOnClickListener {
            addViewModel.todo.title = txtTitle.text.toString()

            val message = addViewModel.save()
            if (message != null) {
                Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
            } else {
                finish()
            }
        }

        clearDue.setOnClickListener {
            addViewModel.todo.dueDate = null
            clearDue.visibility = View.INVISIBLE
            due.visibility = View.INVISIBLE
            setDue.visibility = View.VISIBLE
        }

        setDue.setOnClickListener {
            clearDue.visibility = View.VISIBLE
            due.visibility = View.VISIBLE
            setDue.visibility = View.INVISIBLE
        }

    }
}
