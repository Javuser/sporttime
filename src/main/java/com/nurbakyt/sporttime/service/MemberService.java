package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> findAll();
    void save(Member member);
    Optional<Member> findById(Long memberId);
    void deleteById(Long memberId);
}
