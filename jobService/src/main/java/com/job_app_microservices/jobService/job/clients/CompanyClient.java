package com.job_app_microservices.jobService.job.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.job_app_microservices.jobService.job.external.Company;

@FeignClient(name="companyService")
public interface CompanyClient {
	
	@GetMapping("/company/{id}")
	Company getCompany(@PathVariable("id") Long id);

}
