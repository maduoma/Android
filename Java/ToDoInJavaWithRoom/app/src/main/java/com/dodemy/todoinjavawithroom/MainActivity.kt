package com.dodemy.todoinjavawithroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.todoinjavawithroom.TaskAdapter.ItemClickListener
import com.dodemy.todoinjavawithroom.database.AppDatabase
import com.dodemy.todoinjavawithroom.database.AppExecutors.Companion.instance
import com.dodemy.todoinjavawithroom.database.TaskEntry
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ItemClickListener {
    // Member variables for the adapter and RecyclerView
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: TaskAdapter? = null
    private val mDb: AppDatabase? = null
    var viewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mDb = AppDatabase.getInstance(getApplicationContext());

        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.recyclerViewTasks)

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView?.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = TaskAdapter(this, this)
        setupViewModel()
        mRecyclerView?.adapter = mAdapter
        val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        mRecyclerView?.addItemDecoration(decoration)

        /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            // Called when a user swipes left or right on a ViewHolder
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                // Here is where you'll implement swipe to delete
                // MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
                instance!!.diskIO().execute {
                    val position = viewHolder.adapterPosition
                    val tasks = mAdapter!!.tasks
                    //mDb.taskDao().deleteTask(tasks.get(position));
                    viewModel!!.deleteTask(tasks!![position])
                }
            }
        }).attachToRecyclerView(mRecyclerView)

        /*
         Set the Floating Action Button (FAB) to its corresponding View.
         Attach an OnClickListener to it, so that when it's clicked, a new intent will be created
         to launch the AddTaskActivity.
         */
        val fabButton = findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener { // Create a new intent to start an AddTaskActivity
            val addTaskIntent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivity(addTaskIntent)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel!!.tasks!!.observe(this) { taskEntries ->
            Log.d(TAG, "Updating list of tasks from LiveData in ViewModel")
            mAdapter!!.tasks = taskEntries as List<TaskEntry>?
            //mAdapter.setTasks(taskEntries)
            mAdapter!!.notifyDataSetChanged() //optional statement. will work the same without also
        }
    }

    override fun onItemClickListener(itemId: Int) {
        // Launch AddTaskActivity adding the itemId as an extra in the intent
        // COMPLETED (2) Launch AddTaskActivity with itemId as extra for the key AddTaskActivity.EXTRA_TASK_ID
        val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
        intent.putExtra(AddTaskActivity.Companion.EXTRA_TASK_ID, itemId)
        startActivity(intent)
    }

    companion object {
        // Constant for logging
        private val TAG = MainActivity::class.java.simpleName
    }
}