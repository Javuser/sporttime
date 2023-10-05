package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
