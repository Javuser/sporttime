package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String IIN;
    private String pNumber;

    @OneToMany(mappedBy = "member")
    private List<Membership> memberships;

    public Member(Long id, String name, Integer age, String IIN, String pNumber, List<Membership> memberships) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.IIN = IIN;
        this.pNumber = pNumber;
        this.memberships = memberships;
    }

    public Member() {
    }
}
