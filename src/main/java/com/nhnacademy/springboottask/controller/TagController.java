package com.nhnacademy.springboottask.controller;

import com.nhnacademy.springboottask.domain.Tag;
import com.nhnacademy.springboottask.dto.request.TagRequest;
import com.nhnacademy.springboottask.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/tags")
    public ResponseEntity<Void> createTag(@RequestBody TagRequest request) {
        tagService.createTag(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/tags/{tagId}")
    public ResponseEntity<Void> updateTag(@PathVariable Long tagId, @RequestBody TagRequest request) {
        tagService.updateTag(tagId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.getTags();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tags);
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Tag> getTag(@PathVariable Long tagId) {
        Tag tag = tagService.getTag(tagId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tag);
    }

    @GetMapping("/{taskId}/tags")
    public ResponseEntity<List<Tag>> getTagByTask(@PathVariable Long taskId) {
        List<Tag> tagByTask = tagService.getTagByTask(taskId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tagByTask);
    }
}
