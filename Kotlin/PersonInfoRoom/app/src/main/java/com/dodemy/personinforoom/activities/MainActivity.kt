package com.dodemy.personinforoom.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.personinforoom.R
import com.dodemy.personinforoom.adaptors.PersonAdaptor
import com.dodemy.personinforoom.database.AppDatabase
import com.dodemy.personinforoom.database.AppExecutors
import com.dodemy.personinforoom.model.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    var floatingActionButton: FloatingActionButton? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: PersonAdaptor? = null
    private var mDb: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton = findViewById(R.id.addFAB)
        floatingActionButton?.setOnClickListener {
                startActivity(Intent(this@MainActivity, EditActivity::class.java))
        }
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = PersonAdaptor(this)
        mRecyclerView?.adapter = mAdapter
        mDb = AppDatabase.getInstance(applicationContext)
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Called when a user swipes left or right on a ViewHolder
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                // Here is where you'll implement swipe to delete
                AppExecutors.instance!!.diskIO().execute {
                    val position = viewHolder.adapterPosition
                    val tasks: List<Person>? = mAdapter!!.tasks
                    mDb?.personDao()?.delete(tasks!![position])
                    retrieveTasks()
                }
            }
        }).attachToRecyclerView(mRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        retrieveTasks()
    }

    private fun retrieveTasks() {
        AppExecutors.instance!!.diskIO().execute {
            val persons: List<Person?>? = mDb!!.personDao()!!.loadAllPersons()
            runOnUiThread {
                mAdapter!!.tasks = persons as List<Person>?
            }
        }
    }
}
