package com.nurbakyt.sporttime.controller;

import com.nurbakyt.sporttime.dto.MembershipDto;
import com.nurbakyt.sporttime.entity.Membership;
import com.nurbakyt.sporttime.exception.EntityNotFoundException;
import com.nurbakyt.sporttime.service.MembershipServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipServiceImpl membershipService;

    public MembershipController(MembershipServiceImpl membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping
    private String getMembership(Model model){
        List<MembershipDto> membership = membershipService.findAll()
                .stream()
                .map(MembershipDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("membership", membership);
        return "membership/membership_list";
    }

    @PostMapping("/save_membership")
    public String createMembership(@ModelAttribute MembershipDto membership){
        membershipService.save(membership.toEntity());
        return "redirect:/membership";
    }

    @GetMapping("/{membershipId}/delete")
    public String deleteMembership(@PathVariable Long membershipId){
        membershipService.deleteById(membershipId);
        return "redirect:/membership";
    }
}
