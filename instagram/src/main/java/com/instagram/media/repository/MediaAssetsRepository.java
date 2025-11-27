package com.instagram.media.repository;

import com.instagram.media.domain.MediaAssets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaAssetsRepository extends JpaRepository<MediaAssets, String> {
}
