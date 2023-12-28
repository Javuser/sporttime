package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.UserDemo;

import java.util.Optional;

public interface UserDemoService {
    Optional<UserDemo> findById(Long id);
    void save(UserDemo userDemo);
}
