package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Member;
import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.domain.ProjectStatus;
import com.nhnacademy.springboottask.dto.request.CreateProjectRequest;
import com.nhnacademy.springboottask.exception.ProjectCreateException;
import com.nhnacademy.springboottask.exception.ProjectNotFoundException;
import com.nhnacademy.springboottask.exception.ProjectUpdateException;
import com.nhnacademy.springboottask.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectStatusRepository projectStatusRepository, MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.projectStatusRepository = projectStatusRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    @Override
    public void createProject(CreateProjectRequest request) {
        Project project = new Project();
        ProjectStatus projectStatus = projectStatusRepository
                .findById(request.getProjectState())
                .orElseThrow(ProjectCreateException::new);
        Member member = new Member();
        Member.Pk pk = new Member.Pk();

        project.setProjectStatus(projectStatus);
        project.setProjectName(request.getProjectName());
        project.setUserId(request.getMemberId());

        projectRepository.save(project);

        pk.setProjectId(project.getProjectId());
        pk.setMemberId(request.getMemberId());
        member.setPk(pk);
        member.setProject(project);

        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void updateStateProject(Long projectId, String projectState) {
        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(ProjectUpdateException::new);
        ProjectStatus projectStatus = projectStatusRepository
                .findById(projectState)
                .orElseThrow(ProjectUpdateException::new);
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
        return projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
    }
}
