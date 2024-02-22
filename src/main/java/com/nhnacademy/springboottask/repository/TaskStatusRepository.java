package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, String> {
}
