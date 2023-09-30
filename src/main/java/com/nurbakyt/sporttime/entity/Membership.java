package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Membership(Long id, String type, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Membership() {
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}