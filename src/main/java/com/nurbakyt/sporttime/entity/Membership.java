package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.*;

@Entity
@Data
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Membership(Long id, String type, LocalDate startDate, LocalDate endDate, Integer price) {
        this.id = id;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Membership() {
    }
}