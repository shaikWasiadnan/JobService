package com.example.JobService.dto;

import lombok.Data;

@Data
public class JobMessageDto {
    private Long jobId;
    private String jobTitle;
    private String jobDescription;
}
