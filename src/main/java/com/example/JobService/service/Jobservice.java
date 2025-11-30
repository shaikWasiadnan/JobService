package com.example.JobService.service;

import com.example.JobService.entity.Job;
import com.example.JobService.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Jobservice {
    private JobRepository jobRepository;
    public Jobservice(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    public Job createJob(Job job){
        return jobRepository.save(job);
    }
    public Job getJobById(Long id) throws Exception{
        Optional<Job> job = jobRepository.findById(id);
        if(job.isPresent()){
            Job job1 = job.get();
            return job1;
        }
        else{
            throw new RuntimeException("Job doesn't exist");
        }
    }
    public List<Job> getJobPostedByRecruiter(Long recruiterId){
        return jobRepository.findByRecruiterId(recruiterId);
    }
}

