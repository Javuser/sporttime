package com.nurbakyt.sporttime.repository;

import com.nurbakyt.sporttime.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
