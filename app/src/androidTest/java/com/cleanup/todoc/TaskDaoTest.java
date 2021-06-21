package com.cleanup.todoc;


import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.requeteSql.SaveTaskDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private SaveTaskDataBase database;

    private static int red = 0;
    private static int green = 255;
    private static int blue = 0;
    private static int RGB = android.graphics.Color.argb(255, red, green, blue);
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Tartampion", RGB);
    private static Task NEW_TASK = new Task(1, "nouvelle tache ", 1510308123);



    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SaveTaskDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetUser() throws InterruptedException {

        this.database.projectDao().createProject(PROJECT_DEMO);
        Project project = LiveDataTestUtil.getValue(this.database.projectDao().getProjectId(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
    }

    @Test
    public void getItemsWhenNoItemInserted() throws InterruptedException {

        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(PROJECT_ID));
        assertTrue(task.isEmpty());
    }

    @Test
    public void insertAndGetItems() throws InterruptedException {

        this.database.projectDao().createProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK);

        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(PROJECT_ID));
        assertTrue(tasks.size() == 1);
    }

    @Test
    public void insertAndUpdateItem() throws InterruptedException {

        this.database.projectDao().createProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK);
        Task taskAdded = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(PROJECT_ID)).get(0);
        taskAdded.setSelected(true);
        this.database.taskDao().updateTask(taskAdded);

        List<Task> items = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(PROJECT_ID));
        assertTrue(items.size() == 1 && items.get(0).getSelected());
    }

    @Test
    public void insertAndDeleteItem() throws InterruptedException {

        this.database.projectDao().createProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(NEW_TASK);
        Task taskAdded = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(PROJECT_ID)).get(0);
        this.database.taskDao().deleteTask(taskAdded.getId());

        List<Task> items = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList(PROJECT_ID));
        assertTrue(items.isEmpty());
    }

}
