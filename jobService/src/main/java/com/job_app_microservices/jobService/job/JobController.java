package com.job_app_microservices.jobService.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job_app_microservices.jobService.job.dto.JobWithCompanyDTO;



@RestController
@RequestMapping("/jobs")
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("Server Running");
	}
	
	@GetMapping
	public ResponseEntity<List<JobWithCompanyDTO>> getAllJobs(){
		return ResponseEntity.ok(jobService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id) {
		JobWithCompanyDTO job = jobService.findJobById(id);
		if (job!=null) {
			return new ResponseEntity<> (job,HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<> ("Job with id: "+job.getId()+" has been succesfully created",HttpStatus.CREATED);
	}	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		boolean deleted = jobService.deleteJob(id);
		if (deleted)
			return new ResponseEntity<> ("Job with id: "+id+" has been successfully deleted",HttpStatus.OK);
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@RequestBody Job job,@PathVariable Long id){
		boolean updated = jobService.updateJob(job,id);
		if (updated) return new ResponseEntity<> ("Job with id: "+id+" has been successfully updated",HttpStatus.OK);
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}		
	
	
}
