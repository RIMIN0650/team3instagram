package com.instagram.hashtag.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hashtag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "tag_name", nullable = false, unique = true)
    private String tagName;

}
