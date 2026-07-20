package com.careerlens.ats;

import com.careerlens.util.CourseRecommendation;
import com.careerlens.util.ProjectRecommendation;
import org.springframework.stereotype.Service;

@Service
public class ATSService {

    private final SkillMatcher skillMatcher;
    private final ScoreCalculator scoreCalculator;

    public ATSService(SkillMatcher skillMatcher,
                      ScoreCalculator scoreCalculator) {

        this.skillMatcher = skillMatcher;
        this.scoreCalculator = scoreCalculator;
    }

    public ATSResult analyzeResume(String resumeText,String jobRole){

        ATSResult result = skillMatcher.matchSkills(resumeText,jobRole);

        int score = scoreCalculator.calculateScore(

                result.getMatchedSkills().size(),
                result.getMatchedSkills().size() + result.getMissingSkills().size()

        );

        result.setAtsScore(score);

        result.setRecommendedProjects(
                ProjectRecommendation.getProjects(result.getMissingSkills())
        );

        result.setRecommendedCourses(
                CourseRecommendation.getCourses(result.getMissingSkills())
        );

        return result;

    }

}