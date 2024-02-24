package com.nhnacademy.springboottask.service;

import com.nhnacademy.springboottask.domain.Milestone;
import com.nhnacademy.springboottask.dto.request.MilestoneRequest;

import java.util.List;

public interface MilestoneService {
    void createMilestone(Long projectId, MilestoneRequest request);
    void updateMilestone(Long milestoneId, Long projectId, MilestoneRequest request);
    void deleteMilestone(Long milestoneId);
    List<Milestone> getMilestones();
    Milestone getMilestone(Long milestoneId);
    List<Milestone> getMilestoneByProject(Long projectId);
}
