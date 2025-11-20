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

}
