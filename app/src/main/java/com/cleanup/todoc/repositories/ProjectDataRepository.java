package com.cleanup.todoc.repositories;


import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.requeteSql.dao.ProjectDao;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;


    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public LiveData<Project> getProject(String projectName) {
        return this.projectDao.getProject(projectName);
    }

    public LiveData<List<Project>> getAllProjects() {
        return this.projectDao.getAllProjects();
    }

    public LiveData<Project> getProjectId(long projectId) {
        return this.projectDao.getProjectId(projectId);
    }
}
