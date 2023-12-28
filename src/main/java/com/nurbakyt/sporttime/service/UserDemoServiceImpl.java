package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.UserDemo;
import com.nurbakyt.sporttime.repository.UserDemoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDemoServiceImpl implements UserDemoService{

    private final UserDemoRepository userDemoRepository;

    public UserDemoServiceImpl(UserDemoRepository userDemoRepository) {
        this.userDemoRepository = userDemoRepository;
    }

    @Override
    public Optional<UserDemo> findById(Long id) {
        return userDemoRepository.findById(id);
    }

    @Override
    public void save(UserDemo userDemo) {
        userDemoRepository.save(userDemo);
    }
}
