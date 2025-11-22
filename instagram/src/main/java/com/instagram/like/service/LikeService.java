package com.instagram.like.service;

import com.instagram.like.domain.Like;
import com.instagram.like.domain.LikeId;
import com.instagram.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    
    // 게시물 별 싫어요 개수 세기
    public int dislikeCount(int postId){
        return likeRepository.countByLikeStatusAndPostId(0, postId);
    }

    // 특정 게시물에 특정 사용자가 좋아요 눌렀는지 확인
    public boolean isLikeByUser(String userId, int postId){
        int count = likeRepository.countByLikeStatusAndPostIdAndUserId(1, postId, userId);
        return count > 0;
    }
    
    // 특정 게시물에 특정 사용자가 싫어요 눌렀는지 확인
    public boolean isDislikeByUser(String userId, int postId){
        int count = likeRepository.countByLikeStatusAndPostIdAndUserId(0, postId, userId);
        return count > 0;
    }
    
    // 특정 게시물 특정 사용자 반응 삭제하기
    public Like deleteLike(int postId, String userId){
        Like like = likeRepository.findByPostIdAndUserId(postId, userId);
        if(like != null)
            likeRepository.delete(like);
        return like;
    }

}
