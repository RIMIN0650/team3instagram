package com.instagram.user;

import com.instagram.user.domain.Profile;
import com.instagram.user.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileRestController {

    private final ProfileService profileService;
    
    
    // 사용자 프로필 저장
    @PostMapping("/user/profile")
    public Map<String, String> saveProfile(String displayName, String bio, String gender,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
                                           HttpSession session){

        String userId = (String)session.getAttribute("id");

        Profile profile = profileService.saveProfile(userId, displayName, bio, gender, birthDate);

        Map<String, String> resultMap = new HashMap<>();
        if(profile != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }
    

}
