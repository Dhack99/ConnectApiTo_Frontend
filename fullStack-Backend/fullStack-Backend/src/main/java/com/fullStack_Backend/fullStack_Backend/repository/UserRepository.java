package com.fullStack_Backend.fullStack_Backend.repository;

import com.fullStack_Backend.fullStack_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
