package com.nhnacademy.springboottask.repository.impl;

import com.nhnacademy.springboottask.domain.QProject;
import com.nhnacademy.springboottask.domain.QTag;
import com.nhnacademy.springboottask.domain.QTaskTag;
import com.nhnacademy.springboottask.domain.Tag;
import com.nhnacademy.springboottask.repository.TagRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TagRepositoryImpl extends QuerydslRepositorySupport implements TagRepositoryCustom {
    public TagRepositoryImpl() {
        super(Tag.class);
    }

    @Override
    public List<Tag> getTagByTaskId(Long taskId) {
        QTag tag = QTag.tag;
        QTaskTag taskTag = QTaskTag.taskTag;

        return from(taskTag)
                .rightJoin(taskTag.tag, tag)
                .where(taskTag.task.taskId.eq(taskId))
                .select(tag)
                .fetch();
    }

    @Override
    public List<Tag> getTagByProjectId(Long projectId) {
        QTag tag = QTag.tag;
        QProject project = QProject.project;

        return from(tag)
                .leftJoin(tag.project, project)
                .where(project.projectId.eq(projectId))
                .select(tag)
                .fetch();
    }
}
