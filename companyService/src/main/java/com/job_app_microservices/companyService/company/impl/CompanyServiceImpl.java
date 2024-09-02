package com.job_app_microservices.companyService.company.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.job_app_microservices.companyService.company.Company;
import com.job_app_microservices.companyService.company.CompanyRepository;
import com.job_app_microservices.companyService.company.CompanyService;
import com.job_app_microservices.companyService.company.clients.ReviewClient;
import com.job_app_microservices.companyService.company.dto.ReviewMessage;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepo;
	private ReviewClient reviewClient;

	public CompanyServiceImpl(CompanyRepository companyRepo, ReviewClient reviewClient) {
		super();
		this.companyRepo = companyRepo;
		this.reviewClient = reviewClient;
	}

	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return companyRepo.findAll();

	}

	@Override
	public Company getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return companyRepo.findById(id).orElse(null);
	}

	@Override
	public void addCompany(Company company) {
		// TODO Auto-generated method stub
		companyRepo.save(company);

	}

	@Override
	public boolean updateCompany(Company company, Long id) {
		Company temp = companyRepo.findById(id).orElse(null);
		if (temp == null) {
			return false;
		}
		temp.setDescription(company.getDescription());
		temp.setName(company.getName());
		companyRepo.save(temp);
		return true;

	}

	@Override
	public boolean deleteCompany(Long id) {
		// TODO Auto-generated method stub
		if (companyRepo.existsById(id)) {
			companyRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateCompanyByRating(ReviewMessage reviewMessage) {
//	    Company company = companyRepo.findById(reviewMessage.getCompanyId())
//	            .orElseThrow(() -> 
//	            new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found" + reviewMessage.getCompanyId()));
		Long companyId = reviewMessage.getCompanyId();
		System.out.println("Searching for Company with ID: " + companyId);

		// Find the company by ID
		Company company = companyRepo.findById(companyId).orElse(null);

		// If the company is not found, log a message and return
		if (company == null) {
			System.out.println("Company not found with ID: " + companyId);
			return;
		}
		double avgRating = reviewClient.getAvgRatingForCompany(reviewMessage.getCompanyId());
		company.setRating(avgRating);
		companyRepo.save(company);
	}

}
