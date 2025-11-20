package com.instagram.like.service;

import com.instagram.like.domain.Like;
import com.instagram.like.domain.LikeId;
import com.instagram.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    
    // 사용자 좋아요 싫어요 저장하기
    public Like saveLike(int isLike, int postId, String userId){

        LikeId likeId = new LikeId(postId, userId);

        Like like = Like.builder()
                .likeStatus(isLike)
                .postId(postId)
                .userId(userId)
                .build();

        return likeRepository.save(like);
    }

    // 게시물 별 좋아요 개수 세기
    public int likeCount(int postId){
        return likeRepository.countByLikeStatusAndPostId(1, postId);
    }

    public int dislikeCount(int postId){
        return likeRepository.countByLikeStatusAndPostId(0, postId);
    }

    public boolean isLikeByUser(String userId, int postId){
        int count = likeRepository.countByLikeStatusAndPostIdAndUserId(1, postId, userId);
        return count > 0;
    }

    public boolean isDislikeByUser(String userId, int postId){
        int count = likeRepository.countByLikeStatusAndPostIdAndUserId(0, postId, userId);
        return count > 0;
    }


}
