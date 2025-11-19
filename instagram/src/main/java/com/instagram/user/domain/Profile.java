package com.instagram.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Profile {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "display_name")
    private String displayName;

    private String bio;
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

}
