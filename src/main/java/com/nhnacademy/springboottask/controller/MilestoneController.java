package com.nhnacademy.springboottask.controller;

import com.nhnacademy.springboottask.domain.Milestone;
import com.nhnacademy.springboottask.dto.request.MilestoneRequest;
import com.nhnacademy.springboottask.service.MilestoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class MilestoneController {
    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping("/milestones/{projectId}")
    public ResponseEntity<Void> milestoneSave(@PathVariable Long projectId, @RequestBody MilestoneRequest request) {
        milestoneService.createMilestone(projectId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/milestones/{milestoneId}/{projectId}")
    public ResponseEntity<Void> milestoneUpdate(@PathVariable Long milestoneId, @PathVariable Long projectId, @RequestBody MilestoneRequest request) {
        milestoneService.updateMilestone(milestoneId, projectId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/milestones/{milestoneId}")
    public ResponseEntity<Void> milestoneDelete(@PathVariable Long milestoneId) {
        milestoneService.deleteMilestone(milestoneId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/milestones")
    public ResponseEntity<List<Milestone>> getMilestones() {
        List<Milestone> milestones = milestoneService.getMilestones();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(milestones);
    }

    @GetMapping("/milestones/{milestoneId}")
    public ResponseEntity<Milestone> getMilestone(@PathVariable Long milestoneId) {
        Milestone milestone = milestoneService.getMilestone(milestoneId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(milestone);
    }

    @GetMapping("/{projectId}/milestones")
    public ResponseEntity<List<Milestone>> getMilestoneByProject(@PathVariable Long projectId) {
        List<Milestone> milestonesByProject = milestoneService.getMilestoneByProject(projectId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(milestonesByProject);
    }
}
