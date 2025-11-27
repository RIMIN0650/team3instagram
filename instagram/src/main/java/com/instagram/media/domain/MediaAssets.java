package com.instagram.media.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;

@Entity
@Setter
@Table(name = "media_assets")
public class MediaAssets {

    @Id
    @Column(length = 512)
    private String path;

    private int width;
    private int height;

    @Column(name = "media_type", length = 50)
    private String mediaType;

}
