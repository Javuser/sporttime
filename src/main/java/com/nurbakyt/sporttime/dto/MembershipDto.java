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
}
