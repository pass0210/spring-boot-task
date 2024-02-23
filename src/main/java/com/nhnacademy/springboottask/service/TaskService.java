package com.nhnacademy.springboottask.service;

import com.nhnacademy.springboottask.domain.Task;
import com.nhnacademy.springboottask.dto.request.CreateTaskRequest;
import com.nhnacademy.springboottask.dto.request.UpdateTaskRequest;

import java.util.List;

public interface TaskService {
    void createTask(Long projectId, CreateTaskRequest request);
    void updateTask(Long taskId, UpdateTaskRequest request);
    void deleteTask(Long taskId);
    List<Task> getTaskByProject(Long projectId);
    Task getTaskDetail(Long taskId);
    List<Task> getTaskByTagAndProject(Long tagId, Long projectId);
}
