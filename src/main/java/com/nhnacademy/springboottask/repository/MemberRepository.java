package com.nhnacademy.springboottask.repository;

import com.nhnacademy.springboottask.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Member.Pk> {
}
