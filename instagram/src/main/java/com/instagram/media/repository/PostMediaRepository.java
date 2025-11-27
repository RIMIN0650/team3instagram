package com.instagram.media.repository;

import com.instagram.media.domain.PostMedia;
import com.instagram.media.domain.PostMediaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostMediaRepository extends JpaRepository<PostMedia, PostMediaId> {

    List<PostMedia> findByPostId(Integer postId);
}
