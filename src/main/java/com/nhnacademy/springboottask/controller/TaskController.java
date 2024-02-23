package com.nhnacademy.springboottask.controller;

import com.nhnacademy.springboottask.domain.Task;
import com.nhnacademy.springboottask.dto.request.CreateTaskRequest;
import com.nhnacademy.springboottask.dto.request.UpdateTaskRequest;
import com.nhnacademy.springboottask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Void> taskSave(@PathVariable Long projectId, @RequestBody CreateTaskRequest request) {
        taskService.createTask(projectId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Void> taskUpdate(@PathVariable Long taskId, @RequestBody UpdateTaskRequest request) {
        taskService.updateTask(taskId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> taskDelete(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<Task>> getTaskByProject(@PathVariable Long projectId) {
        List<Task> taskByProject = taskService.getTaskByProject(projectId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskByProject);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTaskDetail(@PathVariable Long taskId) {
        Task taskDetail = taskService.getTaskDetail(taskId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskDetail);
    }

    @GetMapping("/{tagId}/{projectId}/tasks")
    public ResponseEntity<List<Task>> getTaskByTagAndProject(@PathVariable Long tagId, @PathVariable Long projectId) {
        List<Task> taskByTagAndProject = taskService.getTaskByTagAndProject(tagId, projectId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskByTagAndProject);
    }
}
