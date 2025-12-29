package com.example.JobService.service;

import com.example.JobService.dto.CompanyDto;
import com.example.JobService.dto.JobDto;
import com.example.JobService.dto.JobMessageDto;
import com.example.JobService.entity.Job;
import com.example.JobService.external.CompanyClient;
import com.example.JobService.repository.JobRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Jobservice {
    private JobRepository jobRepository;
    private CompanyClient companyClient;
    private RabbitTemplate rabbitTemplate;
    public Jobservice(JobRepository jobRepository,CompanyClient companyClient,RabbitTemplate rabbitTemplate){
        this.jobRepository=jobRepository;
        this.companyClient=companyClient;
        this.rabbitTemplate=rabbitTemplate;
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    public Job createJob(Job job){
        Job savedJob = jobRepository.save(job);
        JobMessageDto message = new JobMessageDto();
        message.setJobId(savedJob.getId());
        message.setJobTitle(savedJob.getTitle());
        message.setJobDescription(savedJob.getDescription());
        rabbitTemplate.convertAndSend("jobQueue",message);
        System.out.println("Sent message to queue related to Job: "+savedJob.getId());
        return savedJob;
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

