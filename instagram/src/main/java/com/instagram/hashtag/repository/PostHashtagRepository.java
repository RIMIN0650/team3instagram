package com.instagram.hashtag.repository;

import com.instagram.hashtag.domain.PostHashtag;
import com.instagram.hashtag.domain.PostHashtagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, PostHashtagId> {
}
