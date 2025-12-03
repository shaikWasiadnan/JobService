package com.example.JobService.dto;

import com.example.JobService.entity.Job;
import lombok.Data;

@Data
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private Long recruiterId;
    private CompanyDto company;

    public void setJob(Job job){
        this.id=job.getId();
        this.title= job.getTitle();
        this.description=job.getDescription();
        this.location=job.getLocation();
        this.salary= job.getSalary();
        this.recruiterId= job.getRecruiterId();
    }
}
