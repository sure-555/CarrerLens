package com.careerlens.ats;

import org.springframework.stereotype.Component;

@Component
public class ScoreCalculator {

    public int calculateScore(int matchedSkills, int totalSkills) {

        if (totalSkills == 0) {
            return 0;
        }

        return (int) (((double) matchedSkills / totalSkills) * 100);
    }
}