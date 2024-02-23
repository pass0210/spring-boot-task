package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Milestone;
import com.nhnacademy.springboottask.dto.request.MilestoneRequest;
import com.nhnacademy.springboottask.repository.MilestoneRepository;
import com.nhnacademy.springboottask.service.MilestoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public MilestoneServiceImpl(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    @Transactional
    @Override
    public void createMilestone(MilestoneRequest request) {
        Milestone milestone = new Milestone();

        setMilestoneValue(milestone, request);
    }

    @Transactional
    @Override
    public void updateMilestone(Long milestoneId, MilestoneRequest request) {
        Milestone milestone = milestoneRepository.findById(milestoneId).orElse(null);

        setMilestoneValue(milestone, request);
    }

    @Transactional
    @Override
    public void deleteMilestone(Long milestoneId) {
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
        return milestoneRepository.findById(milestoneId).orElse(null);
    }

    private void setMilestoneValue(Milestone milestone, MilestoneRequest request) {
        milestone.setStepName(request.getStepName());
        milestone.setStartDate(request.getStartDate());
        milestone.setEndDate(request.getEndDate());

        milestoneRepository.save(milestone);
    }
}
