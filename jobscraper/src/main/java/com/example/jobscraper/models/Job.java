package com.example.jobscraper.models;

import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity representing a job.
 */
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String company;

    @ElementCollection
    @Column(length = 4000) // Increase the length to 4000 characters
    private Map<String, String> jobDetails; // Store headers and contents dynamically

    /**
     * Gets the job ID.
     *
     * @return the job ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the job ID.
     *
     * @param id the job ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the job role.
     *
     * @return the job role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the job role.
     *
     * @param role the job role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company name.
     *
     * @param company the company name
     */
    public void setCompany(String company) {
        this.company = company;
    }

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