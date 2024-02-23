package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
    void deleteByPk_TaskId(Long taskId);
    void deleteByPk_TagId(Long tagId);
}
