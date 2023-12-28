package com.nurbakyt.sporttime.repository;

import com.nurbakyt.sporttime.entity.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDemoRepository extends CrudRepository<UserDemo, Long> {
}
