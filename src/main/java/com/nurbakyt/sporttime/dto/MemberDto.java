package com.nurbakyt.sporttime.dto;

import com.nurbakyt.sporttime.entity.Member;


public class MemberDto {
    private Long id;
    private String name;
    private Integer age;
    private String tgNickname;
    private Integer phoneNumber;


    public static MemberDto toDto(Member entity) {
        MemberDto dto = new MemberDto();
        dto.id = entity.getId();
        dto.age = entity.getAge();
        dto.name = entity.getName();
        dto.tgNickname = entity.getTgNickname();
        dto.phoneNumber = entity.getPhoneNumber();

        return dto;
    }

    public Member toEntity() {
        Member entity = new Member();

        entity.setId(this.id);
        entity.setAge(this.age);
        entity.setName(this.name);
        entity.setTgNickname(this.tgNickname);
        entity.setPhoneNumber(this.phoneNumber);

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

    public String getTgNickname() {
        return tgNickname;
    }

    public void setTgNickname(String tgNickname) {
        this.tgNickname = tgNickname;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
