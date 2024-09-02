package com.job_app_microservices.companyService.company.message_queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.job_app_microservices.companyService.company.CompanyService;
import com.job_app_microservices.companyService.company.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {
	private final CompanyService companyService;

	public ReviewMessageConsumer(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@RabbitListener(queues = "companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage) {
		companyService.updateCompanyByRating(reviewMessage); 
	}
}
