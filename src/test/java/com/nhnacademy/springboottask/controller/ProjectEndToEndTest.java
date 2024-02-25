package com.nhnacademy.springboottask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboottask.domain.Member;
import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.domain.ProjectStatus;
import com.nhnacademy.springboottask.dto.request.CreateProjectRequest;
import com.nhnacademy.springboottask.dto.request.MemberIdRequest;
import com.nhnacademy.springboottask.dto.request.ProjectStateRequest;
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

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectEndToEndTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Order(1)
    @Transactional
    void projectSaveTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateProjectRequest request = new CreateProjectRequest();
        request.setProjectState("활성");
        request.setProjectName("test project");
        request.setMemberId("pass0210");
        mockMvc.perform(post("/projects")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    @Transactional
    void projectStateUpdateTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();
        ProjectStateRequest request = new ProjectStateRequest();
        projectStatus.setProjectState("활성");

        project.setUserId("pass0210");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);
        entityManager.persist(project);
        Long projectId = project.getProjectId();

        request.setState("휴면");

        mockMvc.perform(put("/projects/" + projectId + "/status")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @Transactional
    void projectMemberSaveTest() throws Exception {
        Project project = new Project();
        ObjectMapper objectMapper = new ObjectMapper();
        MemberIdRequest memberIdRequest = new MemberIdRequest();
        ProjectStatus projectStatus = new ProjectStatus();

        projectStatus.setProjectState("활성");
        memberIdRequest.setMemberId("pass0210");
        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);
        entityManager.persist(project);
        Long projectId = project.getProjectId();

        mockMvc.perform(post("/projects/" + projectId + "/members")
                .content(objectMapper.writeValueAsString(memberIdRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(4)
    @Transactional
    void projectMemberDeleteTest() throws Exception {
        Project project = new Project();
        Member member = new Member();
        Member.Pk pk = new Member.Pk();
        ObjectMapper objectMapper = new ObjectMapper();
        MemberIdRequest memberIdRequest = new MemberIdRequest();
        ProjectStatus projectStatus = new ProjectStatus();

        projectStatus.setProjectState("활성");
        memberIdRequest.setMemberId("pass0210");
        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);
        entityManager.persist(project);
        Long projectId = project.getProjectId();

        pk.setProjectId(projectId);
        pk.setMemberId("pass0210");
        member.setPk(pk);
        member.setProject(project);
        entityManager.persist(member);

        mockMvc.perform(delete("/projects/" + projectId + "/members")
                .content(objectMapper.writeValueAsString(memberIdRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(5)
    @Transactional
    void getProjectByMemberTest() throws Exception {
        Project project = new Project();
        Member member = new Member();
        Member.Pk pk = new Member.Pk();
        MemberIdRequest memberIdRequest = new MemberIdRequest();
        ProjectStatus projectStatus = new ProjectStatus();

        projectStatus.setProjectState("testState");
        memberIdRequest.setMemberId("pass0210");
        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);
        entityManager.persist(projectStatus);
        entityManager.persist(project);
        Long projectId = project.getProjectId();

        pk.setProjectId(projectId);
        pk.setMemberId("pass0210");
        member.setPk(pk);
        member.setProject(project);
        entityManager.persist(member);

        mockMvc.perform(get("/projects/" + "pass0210" + "/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId", equalTo("test")));
    }

    @Test
    @Order(6)
    @Transactional
    void getMemberByProjectTest() throws Exception {
        Project project = new Project();
        Member member = new Member();
        Member.Pk pk = new Member.Pk();
        MemberIdRequest memberIdRequest = new MemberIdRequest();
        ProjectStatus projectStatus = new ProjectStatus();

        projectStatus.setProjectState("testState");
        memberIdRequest.setMemberId("pass0210");
        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);
        entityManager.persist(projectStatus);
        entityManager.persist(project);
        Long projectId = project.getProjectId();

        pk.setProjectId(projectId);
        pk.setMemberId("pass0210");
        member.setPk(pk);
        member.setProject(project);
        entityManager.persist(member);

        mockMvc.perform(get("/projects/" + projectId + "/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].pk.memberId", equalTo("pass0210")));
    }

    @Test
    @Order(7)
    @Transactional
    void getProjectTest() throws Exception {
        Project project = new Project();
        ProjectStatus projectStatus = new ProjectStatus();

        projectStatus.setProjectState("testState");
        project.setUserId("test");
        project.setProjectName("test project");
        project.setProjectStatus(projectStatus);

        entityManager.persist(project);
        Long projectId = project.getProjectId();

        mockMvc.perform(get("/projects/" + projectId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", equalTo("test")));
    }
}