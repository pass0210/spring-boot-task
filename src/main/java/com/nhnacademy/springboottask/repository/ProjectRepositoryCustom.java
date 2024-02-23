package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Project;

import java.util.List;

public interface ProjectRepositoryCustom {
    List<Project> getProjectByMemberId(String memberId);
}
