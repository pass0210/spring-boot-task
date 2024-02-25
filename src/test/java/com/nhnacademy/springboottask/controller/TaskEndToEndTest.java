package com.nhnacademy.springboottask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboottask.domain.*;
import com.nhnacademy.springboottask.dto.request.CreateTaskRequest;
import com.nhnacademy.springboottask.dto.request.UpdateTaskRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskEndToEndTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Order(1)
    @Transactional
    void taskSaveTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        CreateTaskRequest request = new CreateTaskRequest();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        request.setTaskState("할 일");
        request.setUserId("test");
        request.setTitle("test title");
        request.setContent("test content");
        request.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));

        mockMvc.perform(post("/projects/" + projectId + "/tasks")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    @Transactional
    void taskUpdateTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Task task = new Task();
        TaskStatus taskStatus = new TaskStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateTaskRequest request = new UpdateTaskRequest();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        taskStatus.setTaskState("할 일");

        task.setTaskStatus(taskStatus);
        task.setUserId("test");
        task.setTitle("test title");
        task.setContent("test content");
        task.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));
        task.setProject(project);

        entityManager.persist(task);
        Long taskId = task.getTaskId();

        request.setProjectId(projectId);
        request.setTaskState("진행중");
        request.setUserId("test");
        request.setTitle("test title");
        request.setContent("test content");
        request.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));

        mockMvc.perform(put("/projects/tasks/" + taskId)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @Transactional
    void taskDeleteTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Task task = new Task();
        TaskStatus taskStatus = new TaskStatus();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);

        taskStatus.setTaskState("할 일");

        task.setTaskStatus(taskStatus);
        task.setUserId("test");
        task.setTitle("test title");
        task.setContent("test content");
        task.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));
        task.setProject(project);

        entityManager.persist(task);
        Long taskId = task.getTaskId();

        mockMvc.perform(delete("/projects/tasks/" + taskId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    @Transactional
    void getTaskByProjectTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Task task = new Task();
        TaskStatus taskStatus = new TaskStatus();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        taskStatus.setTaskState("할 일");

        task.setTaskStatus(taskStatus);
        task.setUserId("test");
        task.setTitle("test title");
        task.setContent("test content");
        task.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));
        task.setProject(project);

        entityManager.persist(task);

        mockMvc.perform(get("/projects/" + projectId + "/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", equalTo("test title")));
    }

    @Test
    @Order(5)
    @Transactional
    void getTaskDetailTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Task task = new Task();
        TaskStatus taskStatus = new TaskStatus();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);

        taskStatus.setTaskState("할 일");

        task.setTaskStatus(taskStatus);
        task.setUserId("test");
        task.setTitle("test title");
        task.setContent("test content");
        task.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));
        task.setProject(project);

        entityManager.persist(task);
        Long taskId = task.getTaskId();

        mockMvc.perform(get("/projects/tasks/" + taskId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", equalTo("test title")));
    }

    @Test
    @Order(6)
    @Transactional
    void getTaskByTagAndProjectTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Tag tag = new Tag();
        TaskTag taskTag = new TaskTag();
        TaskTag.Pk pk = new TaskTag.Pk();
        Task task = new Task();
        TaskStatus taskStatus = new TaskStatus();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        tag.setTagName("test tag");
        tag.setProject(project);

        entityManager.persist(tag);
        Long tagId = tag.getTagId();

        taskStatus.setTaskState("할 일");

        task.setTaskStatus(taskStatus);
        task.setUserId("test");
        task.setTitle("test title");
        task.setContent("test content");
        task.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));
        task.setProject(project);

        entityManager.persist(task);
        Long taskId = task.getTaskId();

        pk.setTagId(tagId);
        pk.setTaskId(taskId);
        taskTag.setPk(pk);
        taskTag.setTag(tag);
        taskTag.setTask(task);

        entityManager.persist(taskTag);

        mockMvc.perform(get("/projects/" + tagId + "/" + projectId + "/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", equalTo("test title")));
    }
}