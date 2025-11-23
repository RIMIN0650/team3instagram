package com.instagram.like.repository;

import com.instagram.like.domain.Like;
import com.instagram.like.domain.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, LikeId> {

    // 좋아요 개수 세기
    public int countByLikeStatusAndPostId(int isLike, int postId);
    // 특정 사람이 좋아요를 했는지 판단하기 위함
    public int countByLikeStatusAndPostIdAndUserId(int isLike, int postId, String userId);

    // 특정 사람의 특정 게시물에 대한 반응 찾기
    public Like findByPostIdAndUserId(int postId, String userId);

    // 사용자가 반응 남긴 게시물 찾기, 좋아요 : 1, 싫어요 : 0
    public List<Like> findByLikeStatusAndUserId(int likeStatus, String userId);

    public int countByPostIdAndLikeStatus(int postId, int isLike);
}
