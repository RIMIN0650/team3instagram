package com.instagram.post;

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
        model.addAttribute("userId", userId);
        model.addAttribute("postDetail", postDetail);
        return "post/detail";
    }

    @GetMapping("/post/hot")
    public String getTop5HotPosts(Model model){

        List<Post> hotPostList = postService.getTop5HotPosts();
        model.addAttribute("hotPostList", hotPostList);

        return "post/hotPost";

    }

}
