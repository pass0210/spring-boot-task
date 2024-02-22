package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> getMemberByProjectId(Long projectId);
}
