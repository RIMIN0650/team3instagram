package com.instagram.hashtag.repository;

import com.instagram.hashtag.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

    Optional<Hashtag> findByTagName(String tagName);

}
