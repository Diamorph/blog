package com.diamorph.blog.jpa;

import com.diamorph.blog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { }
