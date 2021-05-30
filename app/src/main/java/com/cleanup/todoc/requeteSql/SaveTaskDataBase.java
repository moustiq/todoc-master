package com.cleanup.todoc.requeteSql;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.requeteSql.dao.ProjectDao;
import com.cleanup.todoc.requeteSql.dao.TaskDao;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class SaveTaskDataBase extends RoomDatabase {

    private static volatile SaveTaskDataBase INSTANCE;

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    public static SaveTaskDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveTaskDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SaveTaskDataBase.class,
                            "MyDatabase.db").addCallback(prepopulateDatabase()).build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("projectId", 1);
                contentValues.put("name", "tache de test");


                db.insert("task", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}
