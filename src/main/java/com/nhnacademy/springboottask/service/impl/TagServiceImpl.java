package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Tag;
import com.nhnacademy.springboottask.dto.request.TagRequest;
import com.nhnacademy.springboottask.repository.TagRepository;
import com.nhnacademy.springboottask.repository.TaskTagRepository;
import com.nhnacademy.springboottask.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;

    public TagServiceImpl(TagRepository tagRepository, TaskTagRepository taskTagRepository) {
        this.tagRepository = tagRepository;
        this.taskTagRepository = taskTagRepository;
    }

    @Transactional
    @Override
    public void createTag(TagRequest request) {
        Tag tag = new Tag();
        tag.setTagName(request.getTagName());

        tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void updateTag(Long tagId, TagRequest request) {
        Tag tag = tagRepository.findById(tagId).orElse(null);
        tag.setTagName(request.getTagName());

        tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long tagId) {
        taskTagRepository.deleteByPk_TagId(tagId);
        tagRepository.deleteById(tagId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Tag getTag(Long tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getTagByTask(Long taskId) {
        return tagRepository.getTagByTaskId(taskId);
    }
}
