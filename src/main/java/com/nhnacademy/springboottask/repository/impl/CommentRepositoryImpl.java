package com.nhnacademy.springboottask.repository.impl;

import com.nhnacademy.springboottask.domain.Comment;
import com.nhnacademy.springboottask.domain.QComment;
import com.nhnacademy.springboottask.domain.QTask;
import com.nhnacademy.springboottask.repository.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getCommentByTaskId(Long taskId) {
        QComment comment = QComment.comment;
        QTask task = QTask.task;

        return from(comment)
                .leftJoin(comment.task, task)
                .where(task.taskId.eq(taskId))
                .select(comment)
                .fetch();
    }
}
