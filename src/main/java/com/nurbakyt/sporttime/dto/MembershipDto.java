package com.nurbakyt.sporttime.dto;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;

import java.time.LocalDate;

public class MembershipDto {
    private Long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Integer price;
    private MemberDto memberDto;

    public static MembershipDto toDto(Membership entity) {
        MembershipDto dto = new MembershipDto();
        dto.id = entity.getId();
        dto.type = entity.getType();
        dto.startDate = entity.getStartDate();
        dto.endDate = entity.getEndDate();
        dto.status = LocalDate.now().isBefore(entity.getEndDate())
                && ((LocalDate.now().isEqual(entity.getStartDate()) ||(LocalDate.now().isEqual(entity.getStartDate()))))
                ? "YES"
                : "NO";
        dto.price = entity.getPrice();
        dto.memberDto = new MemberDto();
        dto.memberDto.setId(entity.getMember().getId());
        dto.memberDto.setAge(entity.getMember().getAge());
        dto.memberDto.setName(entity.getMember().getName());

        return dto;
    }

    public Membership toEntity() {
        Membership entity = new Membership();
        entity.setId(this.id);
        entity.setType(this.type);
        entity.setStartDate(this.startDate);
        entity.setEndDate(this.endDate);
        entity.setPrice(this.price);

        Member member = new Member();
        member.setId(this.memberDto.getId());
        member.setAge(this.memberDto.getAge());
        member.setName(this.memberDto.getName());
        member.setIIN(this.memberDto.getIIN());
        member.setPNumber(this.memberDto.getpNumber());
        entity.setMember(member);

        return entity;
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

    public LocalDate getEndDate() {
        return endDate;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
