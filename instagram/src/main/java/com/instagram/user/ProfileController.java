package com.instagram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    // 프로필 저장 페이지
    @GetMapping("/user/profile")
    public String setUserProfile(){
        return "user/setProfile";
    }

}
