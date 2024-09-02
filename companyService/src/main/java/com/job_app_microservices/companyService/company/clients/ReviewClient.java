package com.job_app_microservices.companyService.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="reviewService")
public interface ReviewClient {

	@GetMapping("/reviews/averageRating")
	Double getAvgRatingForCompany(@RequestParam("companyId") Long companyId); 
	
}
