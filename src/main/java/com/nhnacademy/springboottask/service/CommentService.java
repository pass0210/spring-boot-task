package com.nhnacademy.springboottask.service;

import com.nhnacademy.springboottask.domain.Comment;
import com.nhnacademy.springboottask.dto.request.CommentRequest;

import java.util.List;

public interface CommentService {
    void createComment(Long taskId, CommentRequest request);
    void updateComment(Long taskId, Long commentId, CommentRequest request);
    void deleteComment(Long commentId);
    Comment getComment(Long commentId);
    List<Comment> getCommentByTask(Long taskId);
}
