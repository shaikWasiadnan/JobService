package com.example.JobService.repository;

import com.example.JobService.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Long> {
     Optional<Job> findById(Long id);
    List<Job> findByRecruiterId(Long recruiterId);
}
