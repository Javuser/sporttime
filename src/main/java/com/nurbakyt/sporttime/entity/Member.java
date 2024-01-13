package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String tgNickname;
    private Integer phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Membership> memberships;

    public Member(Long id, String name, Integer age, String tgNickname, Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.tgNickname = tgNickname;
        this.phoneNumber = phoneNumber;
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
