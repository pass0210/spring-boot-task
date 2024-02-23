package com.nhnacademy.springboottask.controller;

import com.nhnacademy.springboottask.domain.Comment;
import com.nhnacademy.springboottask.dto.request.CommentRequest;
import com.nhnacademy.springboottask.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable Long taskId, @RequestBody CommentRequest request) {
        commentService.createComment(taskId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long taskId, @PathVariable Long commentId, @RequestBody CommentRequest request) {
        commentService.updateComment(taskId, commentId, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comment);
    }

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<List<Comment>> getCommentByTask(@PathVariable Long taskId){
        List<Comment> commentByTask = commentService.getCommentByTask(taskId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentByTask);
    }
}
