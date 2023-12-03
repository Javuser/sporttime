package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.Membership;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    List<Membership> findAllByMemberId(Long memberId);
    List<Membership> findAll();
    void save(Membership membership);
    Optional<Membership> findById(Long memberId);
    void deleteById(Long memberId);
    List<Membership> deleteAllByMemberId(Long memberId);

}
