package com.nhnacademy.springboottask.repository.impl;

import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.domain.QMember;
import com.nhnacademy.springboottask.domain.QProject;
import com.nhnacademy.springboottask.repository.ProjectRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {

    public ProjectRepositoryImpl() {
        super(Project.class);
    }

    @Override
    public List<Project> getProjectByMemberId(String memberId) {
        QProject project = QProject.project;
        QMember member = QMember.member;

        return from(member)
                .rightJoin(member.project, project)
                .where(member.pk.memberId.eq(memberId))
                .select(project)
                .fetch();
    }
}
