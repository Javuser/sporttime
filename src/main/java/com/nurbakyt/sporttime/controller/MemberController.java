package com.nurbakyt.sporttime.controller;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.repository.MemberRepository;
import com.nurbakyt.sporttime.repository.MembershipRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository repository;
    private final MembershipRepository membershipRepository;

    public MemberController(MemberRepository repository, MembershipRepository membershipRepository) {
        this.repository = repository;
        this.membershipRepository = membershipRepository;
    }

    @GetMapping("")
    public String getMembers(Model model){
        List<Member> members = repository.findAll();
        model.addAttribute("members", members);
        return "member/member_list";
    }

    @GetMapping("/{memberId}")
    public String getMemberById(@PathVariable Long memberId,
                                Model model){
        Member member = repository.findById(memberId).orElse(new Member());
        List<Membership> membership = membershipRepository.findAllByMemberId(memberId);
        Collections.sort(membership, Comparator.comparing(Membership::getEndDate).reversed());


        LocalDate today = LocalDate.now();
        for (Membership m : membership) {
            LocalDate endDate = m.getEndDate();

            Period period = Period.between(m.getStartDate(), endDate);

            if (period.getMonths() == 1 && period.getDays() == 0
                    && endDate.getYear() == today.getYear()) {
                m.setStatus("YES");
            } else {
                m.setStatus("NO");
            }
        }
        model.addAttribute("member", member);
        model.addAttribute("membership", membership);
        return "member/member_card";
    }

    @PostMapping("/{memberId}/save_membership")
    public String createMembership(@PathVariable Long memberId,
                                   @ModelAttribute Membership membership){
        final var member = repository.findById(memberId)
                .orElseThrow(() -> new RuntimeException(" "));

        membership.setMember(member);
        membershipRepository.save(membership);
        return "redirect:/membership";
    }

    @GetMapping("/{memberId}/new_membership")
    public String getCreateMembershipForm(Model model, @PathVariable Long memberId){
        model.addAttribute("membership", new Membership());
       // model.addAttribute("membership");
        final var member = repository.findById(memberId)
                .orElseThrow(() -> new RuntimeException(" "));

        return "membership/membership_form";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable Long memberId){
        repository.deleteById(memberId);
        return "redirect:/members";
    }

    @GetMapping("/new_member")
    public String getCreateMembershipForm(Model model){
        model.addAttribute("member", new Member());
        return "member/member_form";
    }
    @PostMapping("/save_member")
    public String createMembership(@ModelAttribute Member member){
        repository.save(member);
        return "redirect:/members";
    }

}
