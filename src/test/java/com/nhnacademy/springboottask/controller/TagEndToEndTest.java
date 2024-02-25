package com.nhnacademy.springboottask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboottask.domain.*;
import com.nhnacademy.springboottask.dto.request.TagRequest;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagEndToEndTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Order(1)
    @Transactional
    void createTagTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        TagRequest request = new TagRequest();

        projectStatus.setProjectState("활성");

        project.setProjectStatus(projectStatus);
        project.setProjectName("test project");
        project.setUserId("test");

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        request.setTagName("test tag");

        mockMvc.perform(post("/projects/tags/" + projectId)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    @Transactional
    void updateTagTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Tag tag = new Tag();
        ObjectMapper objectMapper = new ObjectMapper();
        TagRequest request = new TagRequest();

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

        request.setTagName("update tag");

        mockMvc.perform(put("/projects/tags/" + tagId + "/" + projectId)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @Transactional
    void deleteTagTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Tag tag = new Tag();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);

        tag.setTagName("test tag");
        tag.setProject(project);

        entityManager.persist(tag);
        Long tagId = tag.getTagId();

        mockMvc.perform(delete("/projects/tags/" + tagId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    @Transactional
    void getTagsTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Tag tag = new Tag();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);

        tag.setTagName("test tag");
        tag.setProject(project);

        entityManager.persist(tag);

        mockMvc.perform(get("/projects/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].tagName", equalTo("test tag")));
    }

    @Test
    @Order(5)
    @Transactional
    void getTagTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Tag tag = new Tag();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);

        tag.setTagName("test tag");
        tag.setProject(project);

        entityManager.persist(tag);
        Long tagId = tag.getTagId();

        mockMvc.perform(get("/projects/tags/" + tagId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tagName", equalTo("test tag")));
    }

    @Test
    @Order(6)
    @Transactional
    void getTagByTaskTest() throws Exception {
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

        taskStatus.setTaskState("할 일");

        task.setTaskStatus(taskStatus);
        task.setTitle("test title");
        task.setContent("test content");
        task.setUserId("test");
        task.setProject(project);
        task.setDeadline(LocalDateTime.of(2024, 2, 29, 11, 59, 59));

        entityManager.persist(task);
        Long taskId = task.getTaskId();

        tag.setTagName("test tag");
        tag.setProject(project);

        entityManager.persist(tag);
        Long tagId = tag.getTagId();

        pk.setTaskId(taskId);
        pk.setTagId(tagId);

        taskTag.setPk(pk);
        taskTag.setTask(task);
        taskTag.setTag(tag);

        entityManager.persist(taskTag);

        mockMvc.perform(get("/projects/" + taskId + "/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tagName", equalTo("test tag")));
    }

    @Test
    @Order(7)
    @Transactional
    void getTagByProjectTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        Tag tag = new Tag();

        projectStatus.setProjectState("활성");

        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        tag.setTagName("test tag");
        tag.setProject(project);

        entityManager.persist(tag);

        mockMvc.perform(get("/projects/project/" + projectId + "/tags"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tagName", equalTo("test tag")));
    }
}