package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    void deleteByTask_TaskId(Long taskId);
}
