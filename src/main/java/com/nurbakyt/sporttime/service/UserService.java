package com.nurbakyt.sporttime.service;

import com.nurbakyt.sporttime.entity.Member;
import com.nurbakyt.sporttime.entity.Role;
import com.nurbakyt.sporttime.entity.User;
import com.nurbakyt.sporttime.helper.Roles;
import com.nurbakyt.sporttime.repository.RoleRepository;
import com.nurbakyt.sporttime.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));
    }

    public User save(User user) {

        Role role = roleRepository.findByName(Roles.USER.getRoleName())
                .orElseThrow(()-> new EntityNotFoundException("Role not found"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

}
