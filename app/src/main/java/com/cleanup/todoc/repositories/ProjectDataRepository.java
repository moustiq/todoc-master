package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.requeteSql.dao.ProjectDao;

public class ProjectDataRepository {

    private final ProjectDao projectDao;


    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public LiveData<Project> getProject(long projectId) {
        return this.projectDao.getProject(projectId);
    }
}
