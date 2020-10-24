package com.dodemy.todolistwithroomtesting.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dodemy.todolistwithroomtesting.R
import com.dodemy.todolistwithroomtesting.add.AddActivity
import com.dodemy.todolistwithroomtesting.obtainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class ListActivity : AppCompatActivity(), ToDoAdapter.OnClickListener {
    override fun onCheckboxChecked(id: String) {
        listViewModel.toggleTodo(id)
    }

    private lateinit var listViewModel: ListViewModel
    private var adapter = ToDoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        listViewModel = obtainViewModel(ListViewModel::class.java)

        listTodos.layoutManager = LinearLayoutManager(this)
        listTodos.adapter = adapter
        listViewModel.mAllToDos.observe(this, Observer { toDos ->
            toDos?.let {
                adapter.setToDos(toDos)
            }
        })

        listViewModel.upcomingToDosCount.observe(this, Observer { count ->
            soonValue.text = count.toString()
        })
    }
}
