package com.instagram.like.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="post_like")
@IdClass(LikeId.class)
@Entity
public class Like {

    @Id
    @Column(name="post_id")
    private int postId;
    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="like_status")
    private int likeStatus;
    // 1 : 좋아요, 0 : 싫어요


}
