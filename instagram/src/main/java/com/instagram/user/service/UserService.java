package com.instagram.user.service;

import com.instagram.user.domain.Profile;
import com.instagram.user.domain.User;
import com.instagram.user.repository.ProfileRepository;
import com.instagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public User addUser(String id, String email, String password){

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();

        return userRepository.save(user);
    }

    // 로그인
    public User getUser(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }

    // 이메일 중복 확인
    public boolean isDuplicatedEmail(String email){
        int count = userRepository.countByEmail(email);
        // 1과 같다면 true : 중복
        return count != 0;
    }

    // 비밀번호 재설정
    public User updatePassword(String email, String password){
        User user  = userRepository.findByEmail(email);

        if(user != null){
            user = user.toBuilder()
                    .password(password)
                    .build();

            user = userRepository.save(user);
        }
        return user;
    }
    
    // 사용자 정보 불러오기
    public User findUser(String userId){

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);
        return user;

    }

}
