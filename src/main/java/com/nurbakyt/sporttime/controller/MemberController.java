package com.nurbakyt.sporttime.controller;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.repository.MemberRepository;
import com.nurbakyt.sporttime.repository.MembershipRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("member", member);
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
    public String getCreateMembershipForm(Model model){
        model.addAttribute("membership", new Membership());
        return "membership/membership_form";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable Long memberId){
        repository.deleteById(memberId);
        return "redirect:/members";


        //hello
    }

}
