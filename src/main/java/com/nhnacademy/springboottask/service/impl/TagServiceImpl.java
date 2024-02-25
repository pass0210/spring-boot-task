package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.domain.Tag;
import com.nhnacademy.springboottask.dto.request.TagRequest;
import com.nhnacademy.springboottask.exception.CreateTagException;
import com.nhnacademy.springboottask.exception.DeleteTagException;
import com.nhnacademy.springboottask.exception.TagNotFoundException;
import com.nhnacademy.springboottask.exception.UpdateTagException;
import com.nhnacademy.springboottask.repository.ProjectRepository;
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
    private final ProjectRepository projectRepository;

    public TagServiceImpl(TagRepository tagRepository,
                          TaskTagRepository taskTagRepository,
                          ProjectRepository projectRepository) {
        this.tagRepository = tagRepository;
        this.taskTagRepository = taskTagRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public void createTag(Long projectId, TagRequest request) {
        Tag tag = new Tag();
        Project project = projectRepository.findById(projectId).orElseThrow(CreateTagException::new);
        tag.setTagName(request.getTagName());
        tag.setProject(project);

        tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void updateTag(Long tagId, Long projectId, TagRequest request) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(UpdateTagException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(UpdateTagException::new);
        tag.setTagName(request.getTagName());
        tag.setProject(project);

        tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            throw new DeleteTagException();
        }

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
        return tagRepository.findById(tagId).orElseThrow(TagNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getTagByTask(Long taskId) {
        return tagRepository.getTagByTaskId(taskId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getTagByProject(Long projectId) {
        return tagRepository.getTagByProjectId(projectId);
    }
}
