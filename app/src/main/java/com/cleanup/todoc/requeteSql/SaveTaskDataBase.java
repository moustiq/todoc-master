package com.cleanup.todoc.requeteSql;

import android.content.ContentValues;
import android.content.Context;

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
                            "Project.db").addCallback(prepopulateDatabase()).build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                ContentValues contentValues = new ContentValues();
                Project[] projects = Project.getAllProjects();

                for (Project p : projects) {

                    contentValues.put("id", p.getId());
                    contentValues.put("name", p.getName());
                    contentValues.put("color", p.getColor());

                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);

                }


            }
        };
    }

}
