package com.dodemy.room_notekeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity(private val NoteViewModelFactory: ViewModelProvider.Factory) :
    AppCompatActivity(), NoteListAdapter.OnDeleteClickListener {
    private val notes: List<Note>? = null

    private lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        val noteListAdapter = NoteListAdapter(this, this)
        recyclerview.adapter = noteListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        noteViewModel = ViewModelProvider(this, NoteViewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, {
            notes?.let {
                noteListAdapter.setNotes(notes)
            }
        })
//        noteViewModel.allNotes.observe(
//
//            this, Observer { notes ->
//            notes?.let {
//                noteListAdapter.setNotes(notes)
//            }
//        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // Code to insert note
            val noteId = UUID.randomUUID().toString()
            val note = Note(noteId, data!!.getStringExtra(NewNoteActivity.NOTE_ADDED)!!)
            noteViewModel.insert(note)

            Toast.makeText(
                applicationContext,
                R.string.saved,
                Toast.LENGTH_LONG
            ).show()
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // Code to update the note
            val note = Note(
                data!!.getStringExtra(EditNoteActivity.NOTE_ID)!!,
                data.getStringExtra(EditNoteActivity.UPDATED_NOTE)!!
            )
            noteViewModel.update(note)

            Toast.makeText(
                applicationContext,
                R.string.updated,
                Toast.LENGTH_LONG
            ).show()

        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDeleteClickListener(myNote: Note) {
        // Code for delete
        noteViewModel.delete(myNote)
    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
}