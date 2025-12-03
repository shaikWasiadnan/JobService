package com.example.JobService.service;

import com.example.JobService.dto.CompanyDto;
import com.example.JobService.dto.JobDto;
import com.example.JobService.entity.Job;
import com.example.JobService.external.CompanyClient;
import com.example.JobService.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Jobservice {
    private JobRepository jobRepository;
    private CompanyClient companyClient;
    public Jobservice(JobRepository jobRepository,CompanyClient companyClient){
        this.jobRepository=jobRepository;
        this.companyClient=companyClient;
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    public Job createJob(Job job){
        return jobRepository.save(job);
    }
    public JobDto getJobById(Long id) {
       Job job = jobRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Job Not found"));
       JobDto jobDto = new JobDto();
       jobDto.setJob(job);
        CompanyDto company = companyClient.getCompany(jobDto.getId());
        jobDto.setCompany(company);
        return jobDto;
    }
    public List<Job> getJobPostedByRecruiter(Long recruiterId){
        return jobRepository.findByRecruiterId(recruiterId);
    }
}

