package com.nurbakyt.sporttime;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.repository.MemberRepository;
import com.nurbakyt.sporttime.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;

@SpringBootApplication
public class SporttimeApplication implements CommandLineRunner {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MembershipRepository membershipRepository;

    public static void main(String[] args) {
        SpringApplication.run(SporttimeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Member member = new Member();
        member.setName("Berik");
        member.setAge(20);
        Member saved = repository.save(member);

        Membership membership = new Membership();
        membership.setType("Дневной абонемент");
        membership.setStartDate(LocalDate.parse("2023-11-30"));
        membership.setEndDate(LocalDate.parse("2023-12-31"));
        membership.setMember(saved);

        membershipRepository.save(membership);

    }
}
