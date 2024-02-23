package com.nhnacademy.springboottask.service;

import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.dto.request.CreateProjectRequest;

import java.util.List;

public interface ProjectService {
    void createProject(CreateProjectRequest request);
    void updateStateProject(Long projectId, String projectState);
    List<Project> getProjectByMember(String memberId);
    Project getProject(Long projectId);
}
