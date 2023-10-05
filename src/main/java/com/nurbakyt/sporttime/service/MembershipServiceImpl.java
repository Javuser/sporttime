package com.nurbakyt.sporttime.service;


import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MembershipServiceImpl implements MembershipService{


    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Membership> findAllByMemberId(Long memberId) {
        return membershipRepository.findAllByMemberId(memberId);
    }

    @Override
    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }

    @Override
    public void save(Membership membership) {
        membershipRepository.save(membership);
    }

    @Override
    public Optional<Membership> findById(Long memberId) {
        return membershipRepository.findById(memberId);
    }

    @Override
    public void deleteById(Long memberId) {
        membershipRepository.deleteById(memberId);
    }

    @Override
    public List<Membership> deleteAllByMemberId(Long memberId) {
        return membershipRepository.deleteAllByMemberId(memberId);
    }
}
