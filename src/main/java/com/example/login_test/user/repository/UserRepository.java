package com.example.login_test.user.repository;

import com.example.login_test.user.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TestUser, Long> {

}
