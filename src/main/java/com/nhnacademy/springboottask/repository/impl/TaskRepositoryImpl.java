package com.nhnacademy.springboottask.repository.impl;

import com.nhnacademy.springboottask.domain.*;
import com.nhnacademy.springboottask.repository.TaskRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TaskRepositoryImpl extends QuerydslRepositorySupport implements TaskRepositoryCustom {
    public TaskRepositoryImpl() {
        super(Task.class);
    }

    @Override
    public List<Task> getTaskByProjectId(Long projectId) {
        QTask task = QTask.task;
        QProject project = QProject.project;

        return from(task)
                .leftJoin(task.project, project)
                .where(project.projectId.eq(projectId))
                .select(task)
                .fetch();
    }

    @Override
    public List<Task> getTaskByTagIdAndProjectId(Long tagId, Long projectId) {
        QTaskTag taskTag = QTaskTag.taskTag;
        QTask task = QTask.task;
        QProject project = QProject.project;
        return from(taskTag)
                .rightJoin(taskTag.task, task)
                .leftJoin(task.project, project)
                .where(taskTag.tag.tagId.eq(tagId).and(project.projectId.eq(projectId)))
                .select(task)
                .fetch();
    }
}
