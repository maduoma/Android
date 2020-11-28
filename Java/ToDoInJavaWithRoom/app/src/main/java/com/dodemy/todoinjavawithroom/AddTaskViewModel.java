package com.dodemy.todoinjavawithroom;

import com.dodemy.todoinjavawithroom.database.AppDatabase;
import com.dodemy.todoinjavawithroom.database.TaskEntry;
import com.dodemy.todoinjavawithroom.database.TasksRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AddTaskViewModel extends ViewModel {

    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<TaskEntry> task;
    private final TasksRepository tasksRepository;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddTaskViewModel(AppDatabase database, int taskId) {
       // task = database.taskDao().loadTaskById(taskId);
        tasksRepository = new TasksRepository(database);
        task = tasksRepository.getLoadTaskById(taskId);
    }

    // COMPLETED (7) Create a getter for the task variable
    public LiveData<TaskEntry> getTask() {
        return task;
    }

    public void updateTask(TaskEntry task) {
        tasksRepository.updateTaskById(task);
    }
}
