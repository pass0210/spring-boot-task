package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
