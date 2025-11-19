package com.instagram.user.repository;

import com.instagram.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // 사용자 로그인
    public User findByEmailAndPassword(String email, String password);
    
    // 이메일 개수 세기
    public int countByEmail(String email);
    
    public User findByEmail(String email);

    public Optional<User> findById(String userId);
}
