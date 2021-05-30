package com.cleanup.todoc.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory{

    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final Executor executors;

    public ViewModelFactory(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executors) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executors = executors;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(projectDataSource,taskDataSource, executors);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
