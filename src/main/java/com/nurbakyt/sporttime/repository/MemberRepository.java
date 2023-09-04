package com.nurbakyt.sporttime.repository;

import com.nurbakyt.sporttime.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
