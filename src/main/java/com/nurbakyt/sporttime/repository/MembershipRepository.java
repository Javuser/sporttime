package com.nurbakyt.sporttime.repository;

import com.nurbakyt.sporttime.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findAllByMemberId(Long memberId);
}
