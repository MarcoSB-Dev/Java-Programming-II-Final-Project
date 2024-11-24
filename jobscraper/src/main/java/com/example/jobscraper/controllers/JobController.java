package com.example.jobscraper.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobscraper.models.Job;
import com.example.jobscraper.services.JobService;

/**
 * Controller for handling job-related requests.
 */
@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * Wrapper class for job data.
     */
    public static class JobDataWrapper {
        public String role;
        public String company;
        public List<Map<String, String>> details;
    }

    /**
     * Handles POST requests to create a new job from dynamic data.
     *
     * @param jobDataWrapper the job data wrapper containing role, company, and details
     * @return a ResponseEntity with a success message or an error message
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createJobFromDynamicData(@RequestBody JobDataWrapper jobDataWrapper) {
        String role = jobDataWrapper.role;
        String company = jobDataWrapper.company;
        List<Map<String, String>> details = jobDataWrapper.details;
        // Log the received data using System.out.println
        System.out.println("Received job data: Role: " + role + ", Company: " + company + ", Details: " + details);

        try {
           // Save the job data
            jobService.saveJobFromDynamicData(role, company, details);
            return ResponseEntity.ok("{\"message\": \"Job data received successfully\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Internal Server Error: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Handles GET requests to retrieve all jobs.
     *
     * @return a list of all jobs
     */
    @GetMapping(produces = "application/json")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}