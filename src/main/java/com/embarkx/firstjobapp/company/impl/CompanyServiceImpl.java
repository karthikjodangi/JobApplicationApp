package com.embarkx.firstjobapp.company.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            return company.get();
        }
        return null;

    }

    @Override
    public boolean deleteCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompanyById(Long id,Company updateComp) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setJobs(updateComp.getJobs());
            company.setTitle(updateComp.getTitle());
            company.setDescription(updateComp.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
