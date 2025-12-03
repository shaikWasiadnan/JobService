package com.example.JobService.external;

import com.example.JobService.dto.CompanyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COMPANY-SERVICE")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    CompanyDto getCompany(@PathVariable Long id);

}
