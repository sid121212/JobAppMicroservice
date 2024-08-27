package com.job_app_microservices.companyService.company;

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

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("Server Running");
	}
	
	@GetMapping
	public ResponseEntity<List<Company>> findAll(){
		return ResponseEntity.ok(companyService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyService.getCompanyById(id);
		if (company!=null) {
			return new ResponseEntity<> (company,HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	
	
	
	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody Company company) {
		companyService.addCompany(company);
		return new ResponseEntity<> ("Company with id: "+company.getId()+" has been succesfully created",HttpStatus.CREATED);
	}	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		boolean deleted = companyService.deleteCompany(id);
		if (deleted)
			return new ResponseEntity<> ("Company with id: "+id+" has been successfully deleted",HttpStatus.OK);
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company,@PathVariable Long id){
		boolean updated = companyService.updateCompany(company,id);
		if (updated) return new ResponseEntity<> ("Company with id: "+id+" has been successfully updated",HttpStatus.OK);
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}		
}
