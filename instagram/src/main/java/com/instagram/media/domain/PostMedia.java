package com.instagram.media.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Table(name = "post_media")
@Getter
@IdClass(PostMediaId.class)
public class PostMedia {

    @Id
    @Column(name = "post_id")
    private Integer postId;

    @Id
    @Column(name = "media_path", length = 512)
    private String mediaPath;


}
