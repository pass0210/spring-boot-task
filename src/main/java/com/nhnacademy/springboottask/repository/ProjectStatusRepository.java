package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, String> {
}
