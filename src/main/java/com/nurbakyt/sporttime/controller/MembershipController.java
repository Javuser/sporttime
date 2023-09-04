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
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipRepository repository;

    public MembershipController(MembershipRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private String getMembership(Model model){
        List<Membership> membership = repository.findAll();
        model.addAttribute("membership", membership);
        return "membership/membership_list";
    }

    @PostMapping("/save_membership")
    public String createMembership(@ModelAttribute Membership membership){
        repository.save(membership);
        return "redirect:/membership";
    }

    @GetMapping("/new_membership")
    public String getCreateMembershipForm(Model model){
        model.addAttribute("membership", new Membership());
        return "membership/membership_form";
    }
    @GetMapping("/{membershipId}")
    public String getMemberById(@PathVariable Long membershipId,
                                Model model){
        Membership membership = repository.findById(membershipId).orElse(new Membership());
        model.addAttribute("membership", membership);
        return "membership/membership_card";
    }

    @GetMapping("/{membershipId}/delete")
    public String deleteMember(@PathVariable Long membershipId){
        repository.deleteById(membershipId);
        return "redirect:/membership";
    }


}
