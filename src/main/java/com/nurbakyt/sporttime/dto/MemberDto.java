package com.nurbakyt.sporttime.dto;

import com.nurbakyt.sporttime.entity.Member;


public class MemberDto {
    private Long id;
    private String name;
    private Integer age;
    private String IIN;
    private String pNumber;

    public static MemberDto toDto(Member entity) {
        MemberDto dto = new MemberDto();
        dto.id = entity.getId();
        dto.age = entity.getAge();
        dto.name = entity.getName();
        dto.IIN = entity.getIIN();
        dto.pNumber = entity.getPNumber();
        return dto;
    }

    public Member toEntity() {
        Member entity = new Member();
        entity.setId(this.id);
        entity.setAge(this.age);
        entity.setName(this.name);
        entity.setIIN(this.IIN);
        entity.setPNumber(this.pNumber);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }
}
