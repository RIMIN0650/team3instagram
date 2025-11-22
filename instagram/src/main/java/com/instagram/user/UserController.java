package com.instagram.user;

import com.instagram.post.domain.Post;
import com.instagram.post.service.PostService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final ProfileService profileService;
    private final UserService userService;
    private final PostService postService;

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

    // 비밀번호 변경 페이지
    @GetMapping("/user/password")
    public String changePassword(HttpSession session, Model model){

        String email = (String)session.getAttribute("email");
        model.addAttribute("email", email);


        return "user/changePassword";
    }
    
    // 사용자 정보 조회
    @GetMapping("/user/info")
    public String getUserInfo(String id, Model model){
        
        // 사용자가 입력한 정보가 보여지는 페이지이지만
        // 프로필 페이지로 내용 이전 예정
        
        Profile profile = profileService.getProfile(id);
        User user = userService.findUser(id);

        model.addAttribute("user", user);
        model.addAttribute("profile", profile);

        return "user/viewProfile";
    }

    @GetMapping("/user/mypage")
    public String getUserProfile(String userId, Model model){

        User user = userService.findUser(userId);
        Profile profile = profileService.getProfile(userId);
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);

        // 이 사용자가 올린 게시물 찾기
        List<Post> postList = postService.findPostByUserId(userId);
        // 사용자가 좋아요 누른 게시물 찾기
        List<Post> likePostList = postService.findPostLikeByUser(userId, 1);
        // 사용자가 싫어요 누른 게시물 찾기
        List<Post> dislikePostList = postService.findPostLikeByUser(userId, 0);

        model.addAttribute("postList", postList);
        model.addAttribute("likePostList", likePostList);
        model.addAttribute("dislikePostList", dislikePostList);

        return "user/mypage";
    }


}
