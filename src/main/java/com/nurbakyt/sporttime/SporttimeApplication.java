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
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<Membership> membershipList = membershipRepository.findAllByMemberId(saved.getId());
        System.out.println(membershipList.size());

        LocalDate today = LocalDate.now();
        System.out.println(today);

//        List<Long> idFiltered = (List<Long>) membershipList.get(Math.toIntExact(member.getId()));
        //Predicate<Membership> id = r -> r.getId() = membership.getMember().getId();

//        List<Membership> filteredList;
//
//        filteredList = membershipList.stream()
//                .filter(id -> idFiltered.)

    }
}
