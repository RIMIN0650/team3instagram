package com.instagram;

import com.instagram.post.domain.Post;
import com.instagram.post.dto.PostDetail;
import com.instagram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping("/")
    String returnToMain(){
        return "redirect:/main";
    }


    @GetMapping("/main")
    String mainPage(HttpSession session, Model model) {

        String userId = (String)session.getAttribute("id");
        List<Post> postList = postService.getAllPost();
        model.addAttribute("userId", userId);
        model.addAttribute("postList", postList);

//        String userId = (String)session.getAttribute("id");
//
//        List<PostDetail> postDetailList = postService.getPostDetailList(userId);
//        model.addAttribute("postDetail", postDetailList);

        // 좋아요 순으로 게시물 정렬해서 보여주기
        List<Post> postListOrderByLikeCount = postService.getPostsOrderByLikeCount();
        model.addAttribute("postListOrderByLikeCount", postListOrderByLikeCount);

        List<Post> postListOrderByCreatedAt = postService.getPostListOrderByCreatedAtDesc();
        model.addAttribute("postListOrderByCreatedAt", postListOrderByCreatedAt);

        return "main.html";

    }



}
