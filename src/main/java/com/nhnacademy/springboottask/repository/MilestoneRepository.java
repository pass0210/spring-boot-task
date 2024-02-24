package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> getMilestoneByProject_ProjectId(Long projectId);
}
