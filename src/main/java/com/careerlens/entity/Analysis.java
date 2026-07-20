package com.careerlens.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "analysis")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int atsScore;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String matchedSkills;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String missingSkills;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String recommendedCourses;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String recommendedProjects;

    private String jobRole;

    private LocalDateTime analyzedAt;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}