package com.instagram.post.service;

import com.instagram.post.domain.Post;
import com.instagram.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    
    // 사용자 아이디로 게시물 검색하기
    public List<Post> findPostByUserId(String userId){
        return postRepository.findByUserId(userId);
    }

    // 게시물 수정하기
    public Post updatePost(int postId, String title, String textContent){

        Post post = postRepository.findByPostId(postId);

        if(post != null) {
            post = post.toBuilder()
                    .title(title)
                    .textContent(textContent)
                    .updatedAt(LocalDateTime.now())
                    .build();
            post = postRepository.save(post);
        }

        return post;
    }
    
    // 게시물 아이디로 게시물 검색하기
    public Post findByPostId(int postId){
        return postRepository.findByPostId(postId);
    }

    public Post deletePost(int postId){
        Post post = postRepository.findByPostId(postId);
        if(post != null){
            postRepository.delete(post);
        }
        return post;
    }

}
