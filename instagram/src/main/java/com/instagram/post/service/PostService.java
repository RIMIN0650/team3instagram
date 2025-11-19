package com.instagram.post.service;

import com.instagram.post.domain.Post;
import com.instagram.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 신규 게시물 등록
    public Post addPost(String userId, String title, String textContent){
        Post post = Post.builder()
                .userId(userId)
                .title(title)
                .textContent(textContent)
                .build();

        return postRepository.save(post);
    }
    
    // 모든 게시물 불러오기
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }


}
