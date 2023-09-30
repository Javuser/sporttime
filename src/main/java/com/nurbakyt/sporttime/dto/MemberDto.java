package com.nurbakyt.sporttime.dto;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Membership;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class MemberDto {
    public Long id;
    public String name;
    public Integer age;


    public static MemberDto toDto(Member entity) {
        MemberDto dto = new MemberDto();
        dto.id = entity.getId();
        dto.age = entity.getAge();
        dto.name = entity.getName();

        return dto;
    }

    public Member toEntity() {
        Member entity = new Member();

        entity.setId(this.id);
        entity.setAge(this.age);
        entity.setName(this.name);

        return entity;
    }
}
