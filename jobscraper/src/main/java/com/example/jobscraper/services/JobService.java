package com.example.jobscraper.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.jobscraper.models.Job;
import com.example.jobscraper.repositories.JobRepository;

/**
 * Service class for managing job data.
 */
@Service
public class JobService {

    private final JobRepository jobRepository;

    /**
     * Constructs a JobService with the specified JobRepository.
     *
     * @param jobRepository the job repository
     */
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Saves a job from dynamic data.
     *
     * @param role the job role
     * @param company the company name
     * @param details a list of maps containing job details with headers as keys and contents as values
     * @return the saved job
     */
    public Job saveJobFromDynamicData(String role, String company, List<Map<String, String>> details) {
        Job job = new Job();
        job.setRole(role);
        job.setCompany(company);

        // Convert the List<Map<String, String>> to a single Map<String, String>
        Map<String, String> jobDetails = details.stream()
                .collect(Collectors.toMap(
                        entry -> entry.get("header"), 
                        entry -> entry.get("content"),
                        (existing, replacement) -> existing // Handle duplicate headers
                ));

        job.setJobDetails(jobDetails);

        return jobRepository.save(job);
    }

    /**
     * Retrieves all jobs.
     *
     * @return a list of all jobs
     */
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}