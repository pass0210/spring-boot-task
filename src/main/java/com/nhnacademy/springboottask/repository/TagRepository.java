package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {
}
