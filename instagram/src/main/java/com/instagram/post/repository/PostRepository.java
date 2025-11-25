package com.instagram.post.repository;

import com.instagram.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    public List<Post> findByUserId(String userId);

    public Post findByPostId(int postId);

    public List<Post> findAllByOrderByCreatedAtDesc();





    @Query(value = """
       SELECT p.* FROM post p
       LEFT JOIN `post_like` l ON p.post_id = l.post_id
       GROUP BY p.post_id
       ORDER BY SUM(CASE WHEN l.like_status = 1 THEN 1 ELSE 0 END) DESC
       """, nativeQuery = true)
    List<Post> findAllOrderByLikeCountDesc();

}
