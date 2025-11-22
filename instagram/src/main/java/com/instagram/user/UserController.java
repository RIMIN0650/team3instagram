package com.instagram.user;

import com.instagram.user.domain.Profile;
import com.instagram.user.domain.User;
import com.instagram.user.service.ProfileService;
import com.instagram.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();

        session.removeAttribute("id");
        session.removeAttribute("email");

        return "redirect:/main";
    }

    @GetMapping("/user/password")
    public String changePassword(HttpSession session, Model model){

        String email = (String)session.getAttribute("email");
        model.addAttribute("email", email);


        return "user/changePassword";
    }
    // 사용자 정보 조회
    @GetMapping("/user/info")
    public String getUserInfo(String id, Model model){

        Profile profile = profileService.getProfile(id);
        User user = userService.findUser(id);

        model.addAttribute("user", user);
        model.addAttribute("profile", profile);

        return "user/viewProfile";
    }

}
