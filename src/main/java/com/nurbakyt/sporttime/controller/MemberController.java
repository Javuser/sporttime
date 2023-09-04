package com.nurbakyt.sporttime.controller;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    @GetMapping
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

    @PostMapping("/save_member")
    public String createMember(@ModelAttribute Member member){
        repository.save(member);
        return "redirect:/members";
    }

    @GetMapping("/new_member")
    public String getCreateMemberForm(Model model){
        model.addAttribute("member", new Member());
        return "member/member_form";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable Long memberId){
        repository.deleteById(memberId);
        return "redirect:/members";

    }

}
