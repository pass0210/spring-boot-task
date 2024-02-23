package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {
}
