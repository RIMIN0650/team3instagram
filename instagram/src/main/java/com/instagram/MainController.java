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

    @GetMapping("/main")
    String mainPage(HttpSession session, Model model) {

//        List<Post> postList = postService.getAllPost();
//        model.addAttribute("postList", postList);
//
        String userId = (String)session.getAttribute("id");

        List<PostDetail> postDetailList = postService.getPostDetailList(userId);
        model.addAttribute("postDetail", postDetailList);

        return "main.html";
    }



}
