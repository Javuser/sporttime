package com.nurbakyt.sporttime.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    //private Member member;




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

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}