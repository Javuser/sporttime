package com.nurbakyt.sporttime.dto;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;

import java.time.LocalDate;

public class MembershipDto {
    public Long id;
    public String type;
    public LocalDate startDate;
    public LocalDate endDate;
    public String status;
    public MemberDto memberDto;

    public static MembershipDto toDto(Membership entity) {
        MembershipDto dto = new MembershipDto();
        dto.id = entity.getId();
        dto.type = entity.getType();
        dto.startDate = entity.getStartDate();
        dto.endDate = entity.getEndDate();
        dto.status = LocalDate.now().isBefore(entity.getEndDate())
                ? "YES"
                : "NO";
        dto.memberDto = new MemberDto();
        dto.memberDto.id = entity.getMember().getId();
        dto.memberDto.age = entity.getMember().getAge();
        dto.memberDto.name = entity.getMember().getName();

        return dto;
    }

    public Membership toEntity() {
        Membership entity = new Membership();
        entity.setId(this.id);
        entity.setType(this.type);
        entity.setStartDate(this.startDate);
        entity.setEndDate(this.endDate);

        Member member = new Member();
        member.setId(this.memberDto.id);
        member.setAge(this.memberDto.age);
        member.setName(this.memberDto.name);

        entity.setMember(member);

        return entity;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }
}
