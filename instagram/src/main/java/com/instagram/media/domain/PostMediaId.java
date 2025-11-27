package com.instagram.media.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PostMediaId implements Serializable {
    private Integer postId;
    private String mediaPath;
}
