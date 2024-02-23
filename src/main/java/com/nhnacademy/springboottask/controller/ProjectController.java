package com.nhnacademy.springboottask.controller;

import com.nhnacademy.springboottask.domain.Member;
import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.dto.request.CreateProjectRequest;
import com.nhnacademy.springboottask.dto.request.MemberIdRequest;
import com.nhnacademy.springboottask.dto.request.ProjectStateRequest;
import com.nhnacademy.springboottask.service.MemberService;
import com.nhnacademy.springboottask.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final MemberService memberService;

    public ProjectController(ProjectService projectService, MemberService memberService) {
        this.projectService = projectService;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Void> projectSave(@RequestBody CreateProjectRequest request) {
        projectService.createProject(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{projectId}/status")
    public ResponseEntity<Void> projectStateUpdate(@PathVariable Long projectId, @RequestBody ProjectStateRequest request) {
        projectService.updateStateProject(projectId, request.getState());

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/{projectId}/members")
    public ResponseEntity<Void> projectMemberSave(@PathVariable Long projectId, @RequestBody MemberIdRequest request) {
        memberService.addMember(projectId, request.getMemberId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{projectId}/members")
    public ResponseEntity<Void> projectMemberDelete(@PathVariable Long projectId, @RequestBody MemberIdRequest request) {
        memberService.deleteMember(projectId, request.getMemberId());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{memberId}/projects")
    public ResponseEntity<List<Project>> getProjectByMember(@PathVariable("memberId") String memberId) {
        List<Project> projectByMember = projectService.getProjectByMember(memberId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectByMember);
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<Member>> getMemberByProject(@PathVariable("projectId") Long projectId) {
        List<Member> memberByProject = memberService.getMemberByProject(projectId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberByProject);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId) {
        Project project = projectService.getProject(projectId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(project);
    }
}
