package com.job_app_microservices.companyService.company.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.job_app_microservices.companyService.company.Company;
import com.job_app_microservices.companyService.company.CompanyRepository;
import com.job_app_microservices.companyService.company.CompanyService;



@Service
public class CompanyServiceImpl implements CompanyService{
	
	private CompanyRepository companyRepo;
	

	public CompanyServiceImpl(CompanyRepository companyRepo) {
		super();
		this.companyRepo = companyRepo;
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
		if (temp==null) {
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

}
