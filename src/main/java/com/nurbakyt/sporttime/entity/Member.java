package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private Long tg_chat_id;
    @OneToMany(mappedBy = "member")
    private List<Membership> memberships;

    public Member(Long id, String name, Integer age, Long tg_chat_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.tg_chat_id = tg_chat_id;
    }

    public Member() {
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

    public Long getTg_chat_id() {
        return tg_chat_id;
    }

    public void setTg_chat_id(Long tg_chat_id) {
        this.tg_chat_id = tg_chat_id;
    }
}
