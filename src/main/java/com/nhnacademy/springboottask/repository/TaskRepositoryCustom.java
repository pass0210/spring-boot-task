package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Task;

import java.util.List;

public interface TaskRepositoryCustom {
    List<Task> getTaskByProjectId(Long projectId);
    List<Task> getTaskByTagIdAndProjectId(Long tagId, Long projectId);
}
