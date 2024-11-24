package com.example.jobscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Job Scraper application.
 */
@SpringBootApplication
public class JobscraperApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JobscraperApplication.class, args);
    }
}