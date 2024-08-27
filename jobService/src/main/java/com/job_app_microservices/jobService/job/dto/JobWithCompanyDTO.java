package com.job_app_microservices.jobService.job.dto;

import java.util.List;

import com.job_app_microservices.jobService.job.Job;
import com.job_app_microservices.jobService.job.external.Company;
import com.job_app_microservices.jobService.job.external.Review;

public class JobWithCompanyDTO {
	private Job job;
	private Company company;
	private List<Review> review;
	
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Review> getReview() {
		return review;
	}
	public void setReview(List<Review> reviews) {
		this.review = reviews;
	}

}
