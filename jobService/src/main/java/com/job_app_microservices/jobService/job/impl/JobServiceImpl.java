package com.job_app_microservices.jobService.job.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.job_app_microservices.jobService.job.Job;
import com.job_app_microservices.jobService.job.JobRepository;
import com.job_app_microservices.jobService.job.JobService;
import com.job_app_microservices.jobService.job.dto.JobWithCompanyDTO;
import com.job_app_microservices.jobService.job.external.Company;
import com.job_app_microservices.jobService.job.external.Review;



@Service
public class JobServiceImpl implements JobService{
	
	// private List<Job> jobs = new ArrayList<Job>();
		JobRepository jobRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	public JobServiceImpl(JobRepository jobRepo) {
		super();
		this.jobRepo = jobRepo;
	}

	
	
	@Override
	public List<JobWithCompanyDTO> findAll() {
		
		List<Job> jobs = jobRepo.findAll();
		List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
//		RestTemplate restTemplate = new RestTemplate();
		for (Job job : jobs) {
			JobWithCompanyDTO jobWithCompanyDTO = convertToDTO(job);
			jobWithCompanyDTOS.add(jobWithCompanyDTO);
		}
		
		return jobWithCompanyDTOS;
	}

	@Override
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		
		jobRepo.save(job);
		
	}
	
	private JobWithCompanyDTO convertToDTO(Job job) {
		JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
		jobWithCompanyDTO.setJob(job);
		String url = "http://companyService:8081/company/"+job.getCompanyId();
		String url2 = "http://reviewService:8083/reviews?companyId="+job.getCompanyId();
//		System.out.println(url2);
		Company company = restTemplate.getForObject(url, Company.class);
		Review[] reviewsArray = restTemplate.getForObject(url2, Review[].class);
		List<Review> reviews = Arrays.asList(reviewsArray);
		jobWithCompanyDTO.setCompany(company);
		jobWithCompanyDTO.setReview(reviews);
		return jobWithCompanyDTO;
	}

	@Override
	public JobWithCompanyDTO findJobById(Long jobId) {
		Job job = jobRepo.findById(jobId).orElse(null);
		return convertToDTO(job);
		
	}

	@Override
	public boolean deleteJob(Long id) {
		// TODO Auto-generated method stub
		try {
			jobRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	    
		
	}

	@Override
	public boolean updateJob(Job job, Long id) {
		// TODO Auto-generated method stub
		Job temp = jobRepo.findById(id).orElse(null);
		if (temp==null) {
			return false;
		}
        temp.setDescription(job.getDescription());
        temp.setLocation(job.getLocation());
        temp.setMaxSalary(job.getMaxSalary());
        temp.setMinSalary(job.getMinSalary());
        temp.setTitle(job.getTitle());
        jobRepo.save(temp);
        return true;
		
	}
	
}
