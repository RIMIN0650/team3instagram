package com.instagram.post;

import com.instagram.post.domain.Post;
import com.instagram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/post/newpost")
    public Map<String, String> uploadPost(String title, String textContent, HttpSession session){

        String id = (String)session.getAttribute("id");

        Post post = postService.addPost(id, title, textContent);

        Map<String, String> resultMap = new HashMap<>();

        if(post != null){
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }



}
