package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.requeteSql.dao.TaskDao;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;


    public TaskDataRepository(TaskDao taskDao) {
        this.mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getTasks() {
        return this.mTaskDao.getTaskList();
    }

    public void createTask(Task task) {
        mTaskDao.insertTask(task);
    }

    public LiveData<List<Task>> getTaskName(){
        return mTaskDao.getTaskName();
    }

    public void deleteTask(long taskId) {
        mTaskDao.deleteTask(taskId);
    }

    public void updateTask(Task task) {
        mTaskDao.updateTask(task);
    }

}
