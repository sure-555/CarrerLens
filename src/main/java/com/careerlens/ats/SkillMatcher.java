package com.careerlens.ats;

import com.careerlens.util.SkillDatabase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillMatcher {

    public ATSResult matchSkills(String resumeText, String jobRole){

        List<String> requiredSkills = SkillDatabase.JOB_SKILLS.get(jobRole);

        if (requiredSkills == null) {

            return ATSResult.builder()
                    .matchedSkills(new java.util.ArrayList<>())
                    .missingSkills(new java.util.ArrayList<>())
                    .build();

        }

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        String resume = resumeText.toLowerCase();

        for(String skill : requiredSkills){

            if(resume.contains(skill.toLowerCase())){
                matchedSkills.add(skill);
            }else{
                missingSkills.add(skill);
            }
        }

        return ATSResult.builder()
                .matchedSkills(matchedSkills)
                .missingSkills(missingSkills)
                .build();

    }

}