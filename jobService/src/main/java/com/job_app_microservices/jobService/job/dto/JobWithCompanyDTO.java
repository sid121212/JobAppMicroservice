package com.job_app_microservices.jobService.job.dto;

import com.job_app_microservices.jobService.job.Job;
import com.job_app_microservices.jobService.job.external.Company;

public class JobWithCompanyDTO {
	private Job job;
	private Company company;
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

}
