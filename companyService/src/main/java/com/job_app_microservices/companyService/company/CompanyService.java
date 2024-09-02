package com.job_app_microservices.companyService.company;

import java.util.List;

import com.job_app_microservices.companyService.company.dto.ReviewMessage;


public interface CompanyService {
	List<Company> findAll(); 
	Company getCompanyById(Long id);
	void addCompany(Company company);
	boolean updateCompany(Company company,Long id);
	boolean deleteCompany(Long id);
	void updateCompanyByRating(ReviewMessage reviewMessage);
	
	
	
}
