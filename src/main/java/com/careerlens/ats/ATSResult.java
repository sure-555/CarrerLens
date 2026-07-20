package com.careerlens.ats;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ATSResult {

    private int atsScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private List<String> recommendedProjects;

    private List<String> recommendedCourses;

}