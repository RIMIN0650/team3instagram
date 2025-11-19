package com.instagram.post;

import com.instagram.post.domain.Post;
import com.instagram.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/newpost")
    public String getPostUploadForm(){
        return "post/upload";
    }

    @GetMapping("/post/user")
    public String getPostSearchByUserId(String userId, Model model){
        List<Post> userPostList = postService.findPostByUserId(userId);
        model.addAttribute("userPostList", userPostList);
        return "post/userPost";
    }


}
