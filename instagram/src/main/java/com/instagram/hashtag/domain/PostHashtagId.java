package com.instagram.hashtag.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtagId {

    private int postId;
    private int tagId;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof PostHashtagId)) return false;
        PostHashtagId that = (PostHashtagId) o;
        return postId == that.postId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, tagId);
    }

}
