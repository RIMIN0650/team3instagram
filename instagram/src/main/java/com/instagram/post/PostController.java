package com.instagram.post;

import com.instagram.media.service.MediaService;
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
public class PostController {

    private final PostService postService;
    private final MediaService mediaService;

    // 새로운 게시물 등록
    @GetMapping("/post/newpost")
    public String getPostUploadForm(){
        return "post/upload";
    }

    // 게시물 수정
    @GetMapping("/post/modify")
    public String updatePost(int postId, Model model) {
        Post post = postService.findByPostId(postId);
        model.addAttribute("post", post);
        return "post/modify";
    }
    
    // 게시글 상세페이지
    @GetMapping("/post/detail")
    public String getPostDetail(int postId, Model model, HttpSession session){
        String userId = (String)session.getAttribute("id");
        PostDetail postDetail = postService.getPostDetail(postId, userId);
        // 게시글 사진 가져오기
        List<String> images = mediaService.getPostImagePaths(postId);
        
        model.addAttribute("userId", userId);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("images", images);
        return "post/detail";
    }

    @GetMapping("/post/hot")
    public String getTop5HotPosts(Model model){

        List<Post> hotPostList = postService.getTop5HotPosts();
        model.addAttribute("hotPostList", hotPostList);

        return "post/hotPost";

    }


}
