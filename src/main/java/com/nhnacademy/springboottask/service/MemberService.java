package com.nhnacademy.springboottask.service;

import com.nhnacademy.springboottask.domain.Member;

import java.util.List;

public interface MemberService {
    Member addMember(Long projectId, String memberId);
    void deleteMember(Long projectId, String memberId);
    List<Member> getMemberByProject(Long projectId);
}
