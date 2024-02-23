package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.domain.ProjectStatus;
import com.nhnacademy.springboottask.dto.request.CreateProjectRequest;
import com.nhnacademy.springboottask.repository.ProjectRepository;
import com.nhnacademy.springboottask.repository.ProjectStatusRepository;
import com.nhnacademy.springboottask.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectStatusRepository projectStatusRepository) {
        this.projectRepository = projectRepository;
        this.projectStatusRepository = projectStatusRepository;
    }

    @Transactional
    @Override
    public void createProject(CreateProjectRequest request) {
        Project project = new Project();
        ProjectStatus projectStatus = projectStatusRepository
                .findById(request.getProjectState())
                .orElse(null);

        project.setProjectStatus(projectStatus);
        project.setProjectName(request.getProjectName());
        project.setUserId(request.getMemberId());

        projectRepository.save(project);
    }

    @Transactional
    @Override
    public void updateStateProject(Long projectId, String projectState) {
        Project project = projectRepository
                .findById(projectId)
                .orElse(null);
        ProjectStatus projectStatus = projectStatusRepository
                .findById(projectState)
                .orElse(null);
        project.setProjectStatus(projectStatus);

        projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> getProjectByMember(String memberId) {
        return projectRepository.getProjectByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    @Override
    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }
}
