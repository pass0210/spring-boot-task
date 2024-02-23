package com.nhnacademy.springboottask.repository.impl;

import com.nhnacademy.springboottask.domain.Member;
import com.nhnacademy.springboottask.domain.QMember;
import com.nhnacademy.springboottask.domain.QProject;
import com.nhnacademy.springboottask.repository.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> getMemberByProjectId(Long projectId) {
        QMember member = QMember.member;
        QProject project = QProject.project;

        return from(member)
                .leftJoin(member.project, project)
                .where(project.projectId.eq(projectId))
                .select(member)
                .fetch();
    }
}
