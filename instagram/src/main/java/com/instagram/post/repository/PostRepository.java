package com.instagram.post.repository;

import com.instagram.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    public List<Post> findByUserId(String userId);

    public Post findByPostId(int postId);

}
