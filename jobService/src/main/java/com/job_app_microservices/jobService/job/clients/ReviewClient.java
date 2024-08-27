package com.job_app_microservices.jobService.job.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.job_app_microservices.jobService.job.external.Review;

@FeignClient(name="reviewService")
public interface ReviewClient {
	
	@GetMapping("/reviews")
	List<Review> getAllReviews(@RequestParam("companyId") Long companyId);
}
