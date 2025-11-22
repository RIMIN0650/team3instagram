package com.instagram.like;

import com.instagram.like.domain.Like;
import com.instagram.like.service.LikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LikeRestController {

    private final LikeService likeService;

    // 좋아요 추가 버튼
    @PostMapping("/post/like")
    public Map<String, String> saveLike(int isLike, int postId, HttpSession session){

        String userId = (String)session.getAttribute("id");

        Like like = likeService.saveLike(isLike, postId, userId);

        Map<String, String> resultMap = new HashMap<>();

        if(like != null){
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }

    @DeleteMapping("/post/like")
    public Map<String, String> deleteResponse(int postId, HttpSession session){

        String userId = (String)session.getAttribute("id");
        Like like = likeService.deleteLike(postId, userId);
        Map<String, String> resultMap = new HashMap<>();

        if(like != null){
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }


}
