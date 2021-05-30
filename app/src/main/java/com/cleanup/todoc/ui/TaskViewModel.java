package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final Executor executors;

    private LiveData<Project> currentProject;



    public TaskViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executors) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executors = executors;
    }

    public void init(long projectId) {
        if (this.currentProject != null) {
            return;
        }
        currentProject = projectDataSource.getProject(projectId);
    }

    public LiveData<Project> getTask(long projectId) {
        return this.currentProject;
    }

    public void createTask(Task task) {
        executors.execute(() ->{
                taskDataSource.createTask(task);
        });
    }

    public void deleteTask(long taksId) {
        executors.execute(() ->{
            taskDataSource.deleteTask(taksId);
        });
    }

    public void upadtask(Task task) {
        executors.execute(() ->{
            taskDataSource.updateTask(task);
        });
    }
}
