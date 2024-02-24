package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Tag;

import java.util.List;

public interface TagRepositoryCustom {
    List<Tag> getTagByTaskId(Long taskId);
    List<Tag> getTagByProjectId(Long projectId);
}
