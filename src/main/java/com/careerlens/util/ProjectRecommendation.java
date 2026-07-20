package com.careerlens.util;

import java.util.*;

public class ProjectRecommendation {

    public static List<String> getProjects(List<String> missingSkills){

        Set<String> projects = new LinkedHashSet<>();

        for(String skill : missingSkills){

            switch (skill){

                case "React":
                    projects.add("Netflix Clone");
                    projects.add("Expense Tracker");
                    projects.add("E-Commerce Website");
                    break;

                case "Spring Boot":
                    projects.add("Library Management System");
                    projects.add("Hospital Management System");
                    break;

                case "Docker":
                    projects.add("Dockerize a Spring Boot Application");
                    break;

                case "REST API":
                    projects.add("Student Management REST API");
                    break;

                case "PostgreSQL":
                    projects.add("Employee Database Management");
                    break;

                case "Hibernate":
                    projects.add("Employee CRUD System");
                    break;

                case "Git":
                    projects.add("Git Portfolio Project");
                    break;

                case "NumPy":
                    projects.add("House Price Prediction");
                    break;

                case "TensorFlow":
                    projects.add("Handwritten Digit Recognition");
                    break;

                case "PyTorch":
                    projects.add("Image Classification");
                    break;
            }
        }

        return new ArrayList<>(projects);

    }

}