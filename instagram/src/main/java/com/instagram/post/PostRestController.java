package com.instagram.post;

import com.instagram.media.service.MediaService;
import com.instagram.post.domain.Post;
import com.instagram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;
    private final MediaService mediaService;

    @PostMapping("/post/newpost")
    public Map<String, String> uploadPost(String title
                                        , String textContent
                                        , @RequestParam(required = false, defaultValue = "") String hashtags
                                        , @RequestParam(value = "images", required = false) List<MultipartFile> images
                                        , HttpSession session) throws IOException {

        String id = (String) session.getAttribute("id");

        Post post = postService.addPost(id, title, textContent, hashtags);

        if (images != null && !images.isEmpty()) {
            mediaService.uploadPostImages(post.getPostId(), images);
        }

        Map<String, String> resultMap = new HashMap<>();

        if (post != null) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }


    // 게시물 수정
    @PutMapping("/post")
    public Map<String, String> modifyPost(int postId, String title, String textContent){

        Post post = postService.updatePost(postId, title, textContent);

        Map<String, String> resultMap = new HashMap<>();

        if(post != null){
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }

    @DeleteMapping("/post")
    public Map<String, String> deletePost(int postId){

        Post post = postService.deletePost(postId);

        Map<String, String> resultMap = new HashMap<>();

        if(post != null){
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }


}
