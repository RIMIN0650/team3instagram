package com.instagram.user;

import com.instagram.user.domain.User;
import com.instagram.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    
    // 사용자 회원가입
    @PostMapping("/user/join")
    public Map<String, String> join(String id, String email, String password){

        User user = userService.addUser(id, email, password);

        Map<String, String> resultMap = new HashMap<>();

        if (user != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }

    // 중복확인하기
    @GetMapping("/user/duplicate-email")
    public Map<String, Boolean> isDuplicatedEmail(String email){
        boolean isDuplicatedEmail = userService.isDuplicatedEmail(email);

        Map<String, Boolean> resultMap = new HashMap<>();

        resultMap.put("isDuplicated", isDuplicatedEmail);

        return resultMap;
    }



    
    // 사용자 로그인
    @PostMapping("/user/login")
    public Map<String, String> login (String email, String password, HttpServletRequest request){

        User user = userService.getUser(email, password);

        Map<String, String> resultMap = new HashMap<>();

        if(user != null) {
            HttpSession session = request.getSession();

            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());

            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }

    // 사용자 비밀번호 수정
    @PutMapping("/user/password")
    public Map<String, String> changePassword(String email, String password){
        User user = userService.updatePassword(email, password);

        Map<String, String> resultMap = new HashMap<>();

        if(user != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }




}
