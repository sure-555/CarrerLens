package com.careerlens.util;

import java.util.*;

public class CourseRecommendation {

    public static List<String> getCourses(List<String> missingSkills){

        Set<String> courses = new LinkedHashSet<>();

        for(String skill : missingSkills){

            switch (skill){

                case "React":
                    courses.add("React Official Documentation");
                    courses.add("FreeCodeCamp React Course");
                    break;

                case "Spring Boot":
                    courses.add("Spring Boot Official Documentation");
                    break;

                case "Docker":
                    courses.add("Docker for Beginners");
                    break;

                case "REST API":
                    courses.add("REST API Fundamentals");
                    break;

                case "PostgreSQL":
                    courses.add("PostgreSQL Basics");
                    break;

                case "Hibernate":
                    courses.add("Hibernate ORM Course");
                    break;

                case "Git":
                    courses.add("Git & GitHub Crash Course");
                    break;

                case "NumPy":
                    courses.add("NumPy Complete Guide");
                    break;

                case "TensorFlow":
                    courses.add("TensorFlow for Beginners");
                    break;

                case "PyTorch":
                    courses.add("PyTorch Fundamentals");
                    break;
            }

        }

        return new ArrayList<>(courses);

    }

}