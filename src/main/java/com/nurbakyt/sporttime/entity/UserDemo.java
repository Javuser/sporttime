package com.nurbakyt.sporttime.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "userdemo")
public class UserDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;

    private String lname;

    private String uname;

    private Timestamp registeredat;

    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String userName) {
        this.uname = userName;
    }

    public Timestamp getRegisteredat() {
        return registeredat;
    }

    public void setRegisteredat(Timestamp registeredat) {
        this.registeredat = registeredat;
    }
}
