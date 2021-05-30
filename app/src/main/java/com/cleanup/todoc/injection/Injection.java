package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.requeteSql.SaveTaskDataBase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskDataRepository provideTaskDataResource(Context context) {
        SaveTaskDataBase database = SaveTaskDataBase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static ProjectDataRepository provideProjectDataResource(Context context) {
        SaveTaskDataBase database = SaveTaskDataBase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceTask = provideTaskDataResource(context);
        ProjectDataRepository dataSourceProject = provideProjectDataResource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceProject, dataSourceTask, executor);
    }

}
