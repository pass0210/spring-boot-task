package com.nhnacademy.springboottask.service;

import com.nhnacademy.springboottask.domain.Tag;
import com.nhnacademy.springboottask.dto.request.TagRequest;

import java.util.List;

public interface TagService {
    void createTag(Long projectId, TagRequest request);
    void updateTag(Long tagId, Long projectId, TagRequest request);
    void deleteTag(Long tagId);
    List<Tag> getTags();
    Tag getTag(Long tagId);
    List<Tag> getTagByTask(Long taskId);
    List<Tag> getTagByProject(Long projectId);
}
