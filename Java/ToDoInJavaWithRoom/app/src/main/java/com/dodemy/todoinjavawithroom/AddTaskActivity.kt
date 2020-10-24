package com.dodemy.todoinjavawithroom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dodemy.todoinjavawithroom.database.AppDatabase
import com.dodemy.todoinjavawithroom.database.AppDatabase.Companion.getInstance
import com.dodemy.todoinjavawithroom.database.AppExecutors.Companion.instance
import com.dodemy.todoinjavawithroom.database.TaskEntry
import java.util.*

class AddTaskActivity : AppCompatActivity() {
    // Fields for views
    var mEditText: EditText? = null
    var mRadioGroup: RadioGroup? = null
    var mButton: Button? = null
    private var mTaskId = DEFAULT_TASK_ID

    // Member variable for the Database
    private var mDb: AppDatabase? = null
    var viewModel: AddTaskViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        initViews()
        mDb = getInstance(applicationContext)
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID)
        }
        val intent = intent
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton!!.setText(R.string.update_button)
            if (mTaskId == DEFAULT_TASK_ID) {
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID)
                val factory = AddTaskViewModelFactory(mDb, mTaskId)
                viewModel = ViewModelProvider(this, factory).get(AddTaskViewModel::class.java)
                viewModel.getTask().observe(this, object : Observer<TaskEntry?> {
                    override fun onChanged(taskEntry: TaskEntry?) {
                        viewModel.getTask().removeObserver(this)
                        populateUI(taskEntry)
                    }
                })
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId)
        super.onSaveInstanceState(outState)
    }

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private fun initViews() {
        mEditText = findViewById(R.id.editTextTaskDescription)
        mRadioGroup = findViewById(R.id.radioGroup)
        mButton = findViewById(R.id.saveButton)
        mButton.setOnClickListener(View.OnClickListener { onSaveButtonClicked() })
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param task the taskEntry to populate the UI
     */
    private fun populateUI(task: TaskEntry?) {
        // COMPLETED (7) return if the task is null
        if (task == null) {
            return
        }

        // COMPLETED (8) use the variable task to populate the UI
        mEditText!!.setText(task.description)
        setPriorityInViews(task.priority)
    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    fun onSaveButtonClicked() {
        val description = mEditText!!.text.toString()
        val priority = priorityFromViews
        val date = Date()
        val task = TaskEntry(description, priority, date)
        instance!!.diskIO().execute { // COMPLETED (9) insert the task only if mTaskId matches DEFAULT_TASK_ID
            // Otherwise update it
            // call finish in any case
            if (mTaskId == DEFAULT_TASK_ID) {
                // insert new task
                mDb!!.taskDao().insertTask(task)
            } else {
                //update task
                task.id = mTaskId
                viewModel!!.updateTask(task)
                //                    mDb.taskDao().updateTask(task);
            }
            finish()
        }
    }

    /**
     * getPriority is called whenever the selected priority needs to be retrieved
     */
    val priorityFromViews: Int
        get() {
            var priority = 1
            val checkedId = (findViewById<View>(R.id.radioGroup) as RadioGroup).checkedRadioButtonId
            when (checkedId) {
                R.id.radButton1 -> priority = PRIORITY_HIGH
                R.id.radButton2 -> priority = PRIORITY_MEDIUM
                R.id.radButton3 -> priority = PRIORITY_LOW
            }
            return priority
        }

    /**
     * setPriority is called when we receive a task from MainActivity
     *
     * @param priority the priority value
     */
    private fun setPriorityInViews(priority: Int) {
        when (priority) {
            PRIORITY_HIGH -> (findViewById<View>(R.id.radioGroup) as RadioGroup).check(R.id.radButton1)
            PRIORITY_MEDIUM -> (findViewById<View>(R.id.radioGroup) as RadioGroup).check(R.id.radButton2)
            PRIORITY_LOW -> (findViewById<View>(R.id.radioGroup) as RadioGroup).check(R.id.radButton3)
        }
    }

    companion object {
        // Extra for the task ID to be received in the intent
        const val EXTRA_TASK_ID = "extraTaskId"

        // Extra for the task ID to be received after rotation
        const val INSTANCE_TASK_ID = "instanceTaskId"

        // Constants for priority
        const val PRIORITY_HIGH = 1
        const val PRIORITY_MEDIUM = 2
        const val PRIORITY_LOW = 3

        // Constant for default task id to be used when not in update mode
        private const val DEFAULT_TASK_ID = -1

        // Constant for logging
        private val TAG = AddTaskActivity::class.java.simpleName
    }
}