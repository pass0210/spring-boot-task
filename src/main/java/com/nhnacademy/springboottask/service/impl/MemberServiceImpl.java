package com.nhnacademy.springboottask.service.impl;

import com.nhnacademy.springboottask.domain.Member;
import com.nhnacademy.springboottask.domain.Project;
import com.nhnacademy.springboottask.exception.AddMemberException;
import com.nhnacademy.springboottask.exception.DeleteMemberException;
import com.nhnacademy.springboottask.repository.MemberRepository;
import com.nhnacademy.springboottask.repository.ProjectRepository;
import com.nhnacademy.springboottask.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    public MemberServiceImpl(MemberRepository memberRepository, ProjectRepository projectRepository) {
        this.memberRepository = memberRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public Member addMember(Long projectId, String memberId) {
        Member member = new Member();
        Member.Pk pk = new Member.Pk();
        Project project = projectRepository.findById(projectId).orElseThrow(AddMemberException::new);

        pk.setProjectId(projectId);
        pk.setMemberId(memberId);

        member.setPk(pk);
        member.setProject(project);

        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void deleteMember(Long projectId, String memberId) {
        Project project = projectRepository.findById(projectId).orElseThrow(DeleteMemberException::new);
        if (project.getUserId().equals(memberId)) {
            throw new DeleteMemberException();
        }

        Member.Pk pk = new Member.Pk();

        pk.setProjectId(projectId);
        pk.setMemberId(memberId);

        memberRepository.deleteById(pk);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Member> getMemberByProject(Long projectId) {
        return memberRepository.getMemberByProjectId(projectId);
    }
}
