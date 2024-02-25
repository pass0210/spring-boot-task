package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Comment;
import com.nhnacademy.springboottask.domain.Task;
import com.nhnacademy.springboottask.dto.request.CommentRequest;
import com.nhnacademy.springboottask.repository.CommentRepository;
import com.nhnacademy.springboottask.repository.TaskRepository;
import com.nhnacademy.springboottask.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Override
    public void createComment(Long taskId, CommentRequest request) {
        Comment comment = new Comment();
        Task task = taskRepository.findById(taskId).orElse(null);

        setCommentValue(comment, task, request);
    }

    @Transactional
    @Override
    public void updateComment(Long taskId, Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        Task task = taskRepository.findById(taskId).orElse(null);

        setCommentValue(comment, task, request);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentByTask(Long taskId) {
        return commentRepository.getCommentByTaskId(taskId);
    }

    private void setCommentValue(Comment comment, Task task, CommentRequest request) {
        comment.setTask(task);
        comment.setUserId(request.getUserId());
        comment.setContent(request.getContent());

        commentRepository.save(comment);
    }
}
