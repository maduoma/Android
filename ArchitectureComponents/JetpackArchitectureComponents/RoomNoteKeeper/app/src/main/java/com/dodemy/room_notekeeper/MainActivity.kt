package com.dodemy.room_notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity(private val NoteViewModelFactory: ViewModelProvider.Factory) : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProvider(this, NoteViewModelFactory).get(NoteViewModel::class.java)
    }
}