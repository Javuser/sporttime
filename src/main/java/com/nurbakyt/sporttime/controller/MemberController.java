package com.nurbakyt.sporttime.controller;

import com.nurbakyt.sporttime.dto.MemberDto;
import com.nurbakyt.sporttime.dto.MembershipDto;
import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.service.MemberServiceImpl;
import com.nurbakyt.sporttime.service.MembershipServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceImpl memberService;
    private final MembershipServiceImpl membershipService;

    public MemberController(MemberServiceImpl memberService, MembershipServiceImpl membershipService) {
        this.memberService = memberService;
        this.membershipService = membershipService;
    }

    @GetMapping("")
    public String getMembers(Model model){
        List<MemberDto> members = memberService.findAll()
                .stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("members", members);
        return "member/member_list";
    }

    @GetMapping("/{memberId}")
    public String getMemberById(@PathVariable Long memberId,
                                Model model){
        MemberDto member = memberService.findById(memberId)
                .map(MemberDto::toDto)
                .orElse(new MemberDto());
        List<MembershipDto> membership = membershipService.findAllByMemberId(memberId)
                .stream()
                .map(MembershipDto::toDto)
                .sorted(Comparator.comparing(MembershipDto::getEndDate).reversed())
                .collect(Collectors.toList());

        model.addAttribute("member", member);
        model.addAttribute("membership", membership);
        return "member/member_card";
    }

    @PostMapping("/{memberId}/save_membership")
    public String createMembership(@PathVariable Long memberId,
                                   @ModelAttribute MembershipDto membership){
        MemberDto member = memberService.findById(memberId)
                .map(MemberDto::toDto)
                .orElseThrow(() -> new RuntimeException(" "));

        membership.memberDto = member;
        membershipService.save(membership.toEntity());
        return "redirect:/membership";
    }

    @GetMapping("/{memberId}/new_membership")
    public String getCreateMembershipForm(Model model, @PathVariable Long memberId){
        model.addAttribute("membership", new Membership());
        return "membership/membership_form";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable Long memberId){
       List<MembershipDto> memberships = membershipService.findAllByMemberId(memberId)
                .stream()
                .map(MembershipDto::toDto).collect(Collectors.toList());
        for (MembershipDto membership : memberships) {
            membershipService.deleteById(membership.getId());
        }
        memberService.deleteById(memberId);
        return "redirect:/members";
    }

    @GetMapping("/new_member")
    public String getCreateMembershipForm(Model model){
        model.addAttribute("member", new MemberDto());
        return "member/member_form";
    }
    @PostMapping("/save_member")
    public String createMembership(@ModelAttribute MemberDto member){
        memberService.save(member.toEntity());
        return "redirect:/members";
    }

}
