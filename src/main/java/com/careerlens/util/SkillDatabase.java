package com.careerlens.util;

import java.util.List;
import java.util.Map;

public class SkillDatabase {

    public static final Map<String, List<String>> JOB_SKILLS = Map.of(

            "Java Developer",
            List.of(
                    "Java",
                    "Spring Boot",
                    "Hibernate",
                    "JPA",
                    "PostgreSQL",
                    "REST API",
                    "Git",
                    "Maven"
            ),

            "Frontend Developer",
            List.of(
                    "HTML",
                    "CSS",
                    "JavaScript",
                    "React",
                    "Git",
                    "REST API"
            ),

            "Backend Developer",
            List.of(
                    "Java",
                    "Spring Boot",
                    "PostgreSQL",
                    "Hibernate",
                    "REST API",
                    "Docker",
                    "Git",
                    "Maven"
            ),

            "Full Stack Developer",
            List.of(
                    "HTML",
                    "CSS",
                    "JavaScript",
                    "React",
                    "Java",
                    "Spring Boot",
                    "REST API",
                    "PostgreSQL"
            ),

            "Python Developer",
            List.of(
                    "Python",
                    "Flask",
                    "Django",
                    "SQL",
                    "Git",
                    "REST API"
            ),

            "Data Analyst",
            List.of(
                    "Python",
                    "SQL",
                    "Excel",
                    "Pandas",
                    "NumPy",
                    "Power BI",
                    "Tableau"
            ),

            "AI Engineer",
            List.of(
                    "Python",
                    "NumPy",
                    "Pandas",
                    "Scikit-learn",
                    "TensorFlow",
                    "PyTorch",
                    "SQL",
                    "Git"
            ),

            "Machine Learning Engineer",
            List.of(
                    "Python",
                    "NumPy",
                    "Pandas",
                    "Scikit-learn",
                    "TensorFlow",
                    "PyTorch",
                    "Docker",
                    "Git"
            ),

            "Cloud Engineer",
            List.of(
                    "AWS",
                    "Docker",
                    "Kubernetes",
                    "Linux",
                    "Terraform",
                    "Git",
                    "CI/CD"
            )

    );

}