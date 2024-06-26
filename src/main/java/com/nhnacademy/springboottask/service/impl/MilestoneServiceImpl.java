package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Milestone;
import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.dto.request.MilestoneRequest;
import com.nhnacademy.springboottask.exception.CreateMilestoneException;
import com.nhnacademy.springboottask.exception.DeleteMilestoneException;
import com.nhnacademy.springboottask.exception.MilestoneNotFoundException;
import com.nhnacademy.springboottask.exception.UpdateMilestoneException;
import com.nhnacademy.springboottask.repository.MilestoneRepository;
import com.nhnacademy.springboottask.repository.ProjectRepository;
import com.nhnacademy.springboottask.service.MilestoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public void createMilestone(Long projectId, MilestoneRequest request) {
        Milestone milestone = new Milestone();
        Project project = projectRepository.findById(projectId).orElseThrow(CreateMilestoneException::new);

        setMilestoneValue(milestone, project, request);
    }

    @Transactional
    @Override
    public void updateMilestone(Long milestoneId, Long projectId, MilestoneRequest request) {
        Milestone milestone = milestoneRepository.findById(milestoneId).orElseThrow(UpdateMilestoneException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(UpdateMilestoneException::new);

        setMilestoneValue(milestone, project, request);
    }

    @Transactional
    @Override
    public void deleteMilestone(Long milestoneId) {
        if (!milestoneRepository.existsById(milestoneId))
            throw new DeleteMilestoneException();
        milestoneRepository.deleteById(milestoneId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Milestone> getMilestones() {
        return milestoneRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Milestone getMilestone(Long milestoneId) {
        return milestoneRepository.findById(milestoneId).orElseThrow(MilestoneNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Milestone> getMilestoneByProject(Long projectId) {
        return milestoneRepository.getMilestoneByProject_ProjectId(projectId);
    }

    private void setMilestoneValue(Milestone milestone, Project project, MilestoneRequest request) {
        milestone.setStepName(request.getStepName());
        milestone.setStartDate(request.getStartDate());
        milestone.setEndDate(request.getEndDate());
        milestone.setProject(project);

        milestoneRepository.save(milestone);
    }
}
