package com.example.jobscraper.dto;

import java.util.Map;

/**
 * Data Transfer Object (DTO) for job details.
 */
public class JobDTO {

    private Map<String, String> jobDetails; // Store headers and contents dynamically

    /**
     * Gets the job details.
     *
     * @return a map containing job details with headers as keys and contents as values
     */
    public Map<String, String> getJobDetails() {
        return jobDetails;
    }

    /**
     * Sets the job details.
     *
     * @param jobDetails a map containing job details with headers as keys and contents as values
     */
    public void setJobDetails(Map<String, String> jobDetails) {
        this.jobDetails = jobDetails;
    }
}