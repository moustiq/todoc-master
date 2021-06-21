package com.cleanup.todoc.requeteSql;

import android.content.ContentValues;
import android.content.Context;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.requeteSql.dao.ProjectDao;
import com.cleanup.todoc.requeteSql.dao.TaskDao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Database(version = 3, entities = {Project.class, Task.class}, exportSchema = false)
public abstract class SaveTaskDataBase extends RoomDatabase {

    private static volatile SaveTaskDataBase INSTANCE;

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    public static SaveTaskDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveTaskDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SaveTaskDataBase.class,
                            "Project.db").addCallback(prepopulateDatabase(context)).build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(Context context) {
        return new Callback() {

            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                ContentValues contentValues = new ContentValues();
                List<Project> projects = Arrays.asList(Project.getAllProjects());

                Log.d("pre populate ", "onCreate: Project :: " + projects);

                for (Project p : projects) {

                    Log.d("pre populate BDD", "onCreate: getProject :: " + p.getId());
                    contentValues.put("id", p.getId());
                    contentValues.put("name", p.getName());
                    contentValues.put("color", p.getColor());

                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);

                }


            }
        };
    }

}
