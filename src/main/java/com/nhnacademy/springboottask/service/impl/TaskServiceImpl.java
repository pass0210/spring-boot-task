package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.*;
import com.nhnacademy.springboottask.dto.request.CreateTaskRequest;
import com.nhnacademy.springboottask.dto.request.UpdateTaskRequest;
import com.nhnacademy.springboottask.repository.*;
import com.nhnacademy.springboottask.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskTagRepository taskTagRepository;
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TaskStatusRepository taskStatusRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskTagRepository taskTagRepository,
                           TagRepository tagRepository,
                           ProjectRepository projectRepository,
                           MilestoneRepository milestoneRepository,
                           TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.taskTagRepository = taskTagRepository;
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
        this.milestoneRepository = milestoneRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Transactional
    @Override
    public void createTask(Long projectId, CreateTaskRequest request) {
        Task task = new Task();
        Project project = projectRepository.findById(projectId).orElse(null);
        TaskStatus taskStatus = taskStatusRepository.findById(request.getTaskState()).orElse(null);

        if (Objects.nonNull(request.getMilestoneId())) {
            Milestone milestone = milestoneRepository.findById(request.getMilestoneId()).orElse(null);
            task.setMilestone(milestone);
        }

        task.setProject(project);
        task.setTaskStatus(taskStatus);
        task.setUserId(request.getUserId());
        task.setTitle(request.getTitle());
        task.setContent(request.getContent());
        task.setDeadline(request.getDeadline());

        taskRepository.save(task);

        taskTagSave(task, request.getTagIds());
    }

    @Transactional
    @Override
    public void updateTask(Long taskId, UpdateTaskRequest request) {
        Task task = new Task();
        Project project = projectRepository.findById(request.getProjectId()).orElse(null);
        TaskStatus taskStatus = taskStatusRepository.findById(request.getTaskState()).orElse(null);

        if (Objects.nonNull(request.getMilestoneId())) {
            Milestone milestone = milestoneRepository.findById(request.getMilestoneId()).orElse(null);
            task.setMilestone(milestone);
        }
        task.setTaskId(taskId);
        task.setProject(project);
        task.setTaskStatus(taskStatus);
        task.setUserId(request.getUserId());
        task.setTitle(request.getTitle());
        task.setContent(request.getContent());
        task.setDeadline(request.getDeadline());

        taskRepository.save(task);
        taskTagRepository.deleteByPk_TaskId(task.getTaskId());
        taskTagSave(task, request.getTagIds());
    }

    @Transactional
    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> getTaskByProject(Long projectId) {
        return taskRepository.getTaskByProjectId(projectId);
    }

    @Transactional(readOnly = true)
    @Override
    public Task getTaskDetail(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> getTaskByTagAndProject(Long tagId, Long projectId) {
        return taskRepository.getTaskByTagIdAndProjectId(tagId, projectId);
    }

    private void taskTagSave(Task task, List<Long> tagIds) {
        tagIds.forEach(tagId -> {
            TaskTag taskTag = new TaskTag();
            TaskTag.Pk pk = new TaskTag.Pk();
            Tag tag = tagRepository.findById(tagId).orElse(null);

            pk.setTaskId(task.getTaskId());
            pk.setTagId(tagId);
            taskTag.setPk(pk);
            taskTag.setTag(tag);
            taskTag.setTask(task);

            taskTagRepository.save(taskTag);
        });
    }
}
