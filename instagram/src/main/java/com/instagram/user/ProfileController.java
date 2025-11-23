package com.instagram.user;

import com.instagram.user.domain.User;
import com.instagram.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    // 프로필 저장 페이지
    @GetMapping("/user/profile")
    public String setUserProfile(HttpSession session, Model model){

        String userId = (String)session.getAttribute("id");
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "user/setProfile";
    }

}
