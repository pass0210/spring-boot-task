package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {
}
