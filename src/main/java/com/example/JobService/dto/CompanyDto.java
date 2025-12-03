package com.example.JobService.dto;

import lombok.Data;

@Data
public class CompanyDto {
    public Long companyId;
    public String companyName;
    public String description;
    public String companyAddress;
    public Double rating;
}
