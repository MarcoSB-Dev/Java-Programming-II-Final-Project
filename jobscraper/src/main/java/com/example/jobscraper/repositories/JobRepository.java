package com.example.jobscraper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobscraper.models.Job;

/**
 * Repository interface for accessing job data.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}