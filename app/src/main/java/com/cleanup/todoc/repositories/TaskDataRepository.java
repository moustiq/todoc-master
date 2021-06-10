package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.requeteSql.dao.TaskDao;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;


    public TaskDataRepository(TaskDao taskDao) {
        this.mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getTasks(long projectId) {
        return this.mTaskDao.getTaskList(projectId);
    }

    public void createTask(Task task) {
        mTaskDao.insertTask(task);
    }

    public void deleteTask(long taskId) {
        mTaskDao.deleteTask(taskId);
    }

    public void updateTask(Task task) {
        mTaskDao.updateTask(task);
    }

}
