package com.example.JobService.controller;

import com.example.JobService.dto.JobDto;
import com.example.JobService.entity.Job;
import com.example.JobService.service.Jobservice;
import jakarta.ws.rs.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private Jobservice jobservice;
    public JobController(Jobservice jobservice){
        this.jobservice=jobservice;
    }
    @GetMapping
    public List<Job> getAllJobs(){
        return jobservice.getAllJobs();
    }
    @PostMapping
    public Job postJob(@RequestBody Job job){
        return jobservice.createJob(job);
    }
    @GetMapping("/{id}")
    public JobDto getJobById(@PathVariable Long id) throws Exception {
        return jobservice.getJobById(id);
    }
}
