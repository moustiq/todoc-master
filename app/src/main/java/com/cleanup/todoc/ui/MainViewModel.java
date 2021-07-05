package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final Executor executors;


    public MainViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executors) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executors = executors;
    }

    public LiveData<Project> getProject(String projectName) {
        //return currentProject.getValue().get((int) projectId);
        return projectDataSource.getProject(projectName);
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectDataSource.getAllProjects();
    }

    public LiveData<List<Task>> getTask () {
        return taskDataSource.getTasks();
    }

    public void createTask(Task task) {
        executors.execute(() -> taskDataSource.createTask(task));
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
