package com.careerlens.repository;

import com.careerlens.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

    List<Analysis> findAllByOrderByAnalyzedAtDesc();

}