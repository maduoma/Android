package com.dodemy.todoinjavawithroom.database

import android.net.Uri
import android.provider.BaseColumns

object TaskContract {
    /* Add content provider constants to the Contract
     Clients need to know how to access the task data, and it's your job to provide
     these content URI's for the path to that data:
        1) Content authority,
        2) Base content URI,
        3) Path(s) to the tasks directory
        4) Content URI for data in the TaskEntry class
      */
    // The authority, which is how your code knows which Content Provider to access
    private const val AUTHORITY = "com.example.android.todolist"

    // The base content URI = "content://" + <authority>
    val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY")

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    const val PATH_TASKS = "tasks"

    /* TaskEntry is an inner class that defines the contents of the task table */
    object TaskEntry : BaseColumns {
        // TaskEntry content URI = base content URI + path
        val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build()

        // Task table and column names
        const val TABLE_NAME = "tasks"

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PRIORITY = "priority" /*
        The above table structure looks something like the sample table below.
        With the name of the table and columns on top, and potential contents in rows

        Note: Because this implements BaseColumns, the _id column is generated automatically

        tasks
         - - - - - - - - - - - - - - - - - - - - - -
        | _id  |    description     |    priority   |
         - - - - - - - - - - - - - - - - - - - - - -
        |  1   |  Complete lesson   |       1       |
         - - - - - - - - - - - - - - - - - - - - - -
        |  2   |    Go shopping     |       3       |
         - - - - - - - - - - - - - - - - - - - - - -
        .
        .
        .
         - - - - - - - - - - - - - - - - - - - - - -
        | 43   |   Learn guitar     |       2       |
         - - - - - - - - - - - - - - - - - - - - - -

         */
    }
}