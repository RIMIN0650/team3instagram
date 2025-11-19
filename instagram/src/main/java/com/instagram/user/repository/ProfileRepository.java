package com.instagram.user.repository;

import com.instagram.user.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    public Profile findByUserId(String id);

}
