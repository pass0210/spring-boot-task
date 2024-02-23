package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> getCommentByTaskId(Long taskId);
}
