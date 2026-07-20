package com.careerlens.controller;

import com.careerlens.ats.ATSResult;
import com.careerlens.ats.ATSService;
import com.careerlens.entity.Analysis;
import com.careerlens.entity.Resume;
import com.careerlens.repository.AnalysisRepository;
import com.careerlens.repository.ResumeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ats")
public class ATSController {

    private final ATSService atsService;
    private final ResumeRepository resumeRepository;
    private final AnalysisRepository analysisRepository;

    public ATSController(ATSService atsService,
                         ResumeRepository resumeRepository,
                         AnalysisRepository analysisRepository) {

        this.atsService = atsService;
        this.resumeRepository = resumeRepository;
        this.analysisRepository = analysisRepository;
    }

    @GetMapping("/analyze/{resumeId}")
    public ATSResult analyzeResume(@PathVariable Long resumeId,
                                   @RequestParam("role") String role) {

        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);

        if (optionalResume.isEmpty()) {
            throw new RuntimeException("Resume not found");
        }

        Resume resume = optionalResume.get();

        // Perform ATS Analysis
        ATSResult result = atsService.analyzeResume(
                resume.getResumeText(),
                role
        );

        // Save analysis to database
        Analysis analysis = Analysis.builder()
                .atsScore(result.getAtsScore())
                .matchedSkills(String.join(",", result.getMatchedSkills()))
                .missingSkills(String.join(",", result.getMissingSkills()))
                .recommendedCourses(String.join(",", result.getRecommendedCourses()))
                .recommendedProjects(String.join(",", result.getRecommendedProjects()))
                .jobRole(role)
                .analyzedAt(LocalDateTime.now())
                .resume(resume)
                .build();

        analysisRepository.save(analysis);

        return result;
    }

    @GetMapping("/history")
    public List<Analysis> history() {

        return analysisRepository.findAllByOrderByAnalyzedAtDesc();
    }
}