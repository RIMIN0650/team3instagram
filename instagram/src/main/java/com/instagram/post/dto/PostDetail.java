package com.instagram.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDetail {

    private int postId;
    private String userId;
    private int likeCount;
    private int dislikeCount;
    private boolean isLike;
    private boolean isDislike;
    private String title;
    private String textContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
