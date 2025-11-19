package com.instagram.user.service;

import com.instagram.user.domain.Profile;
import com.instagram.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    // 사용자 개인정보 저장
    public Profile saveProfile(String id, String displayName, String bio, String gender, LocalDate birthDate){

        Profile profile = Profile.builder()
                .userId(id)
                .displayName(displayName)
                .bio(bio)
                .gender(gender)
                .birthDate(birthDate)
                .build();

        return profileRepository.save(profile);
    }

    // 사용자 프로필 검색해서 불러오기
    public Profile getProfile(String id){
        return profileRepository.findByUserId(id);
    }



}
