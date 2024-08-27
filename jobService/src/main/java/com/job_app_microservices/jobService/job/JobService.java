package com.job_app_microservices.jobService.job;

import java.util.List;

import com.job_app_microservices.jobService.job.dto.JobWithCompanyDTO;

public interface JobService {
	
	List<JobWithCompanyDTO> findAll();
	void createJob(Job job);
	JobWithCompanyDTO findJobById(Long id);
	boolean deleteJob(Long id);
	boolean updateJob(Job job, Long id);
	

}
