package com.dodemy.todoinjavawithroom

import android.content.Context
import android.database.Cursor
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dodemy.todoinjavawithroom.database.TaskContract

/**
 * This CustomCursorAdapter creates and binds ViewHolders, that hold the description and priority of a task,
 * to a RecyclerView to efficiently display data.
 */
class CustomCursorAdapter
/**
 * Constructor for the CustomCursorAdapter that initializes the Context.
 *
 * @param mContext the current Context
 */(private val mContext: Context) : RecyclerView.Adapter<CustomCursorAdapter.TaskViewHolder>() {
    // Class variables for the Cursor that holds task data and the Context
    private var mCursor: Cursor? = null

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        // Inflate the task_layout to a view
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, parent, false)
        return TaskViewHolder(view)
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        // Indices for the _id, description, and priority columns
        val idIndex = mCursor!!.getColumnIndex(TaskContract.TaskEntry._ID)
        val descriptionIndex = mCursor!!.getColumnIndex(TaskContract.TaskEntry.COLUMN_DESCRIPTION)
        val priorityIndex = mCursor!!.getColumnIndex(TaskContract.TaskEntry.COLUMN_PRIORITY)
        mCursor!!.moveToPosition(position) // get to the right location in the cursor

        // Determine the values of the wanted data
        val id = mCursor!!.getInt(idIndex)
        val description = mCursor!!.getString(descriptionIndex)
        val priority = mCursor!!.getInt(priorityIndex)

        //Set values
        holder.itemView.tag = id
        holder.taskDescriptionView.text = description

        // Programmatically set the text and color for the priority TextView
        val priorityString = "" + priority // converts int to String
        holder.priorityView.text = priorityString
        val priorityCircle = holder.priorityView.background as GradientDrawable
        // Get the appropriate background color based on the priority
        val priorityColor = getPriorityColor(priority)
        priorityCircle.setColor(priorityColor)
    }

    /*
    Helper method for selecting the correct priority circle color.
    P1 = red, P2 = orange, P3 = yellow
    */
    private fun getPriorityColor(priority: Int): Int {
        var priorityColor = 0
        when (priority) {
            1 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialRed)
            2 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange)
            3 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow)
            else -> {
            }
        }
        return priorityColor
    }

    /**
     * Returns the number of items to display.
     */
    override fun getItemCount(): Int {
        return if (mCursor == null) {
            0
        } else mCursor!!.count
    }

    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    fun swapCursor(c: Cursor?): Cursor? {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor === c) {
            return null // bc nothing has changed
        }
        val temp = mCursor
        mCursor = c // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            notifyDataSetChanged()
        }
        return temp
    }

    // Inner class for creating ViewHolders
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Class variables for the task description and priority TextViews
        var taskDescriptionView: TextView = itemView.findViewById<View>(R.id.taskDescription) as TextView
        var priorityView: TextView = itemView.findViewById<View>(R.id.priorityTextView) as TextView

    }
}