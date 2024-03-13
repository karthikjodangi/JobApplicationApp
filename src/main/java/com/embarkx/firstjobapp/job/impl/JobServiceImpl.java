package com.embarkx.firstjobapp.job.impl;
import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
            jobRepository.save(job);
    }

    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }

    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job_ = jobOptional.get();
            job_.setTitle(job.getTitle());
            job_.setDescription(job.getDescription());
            job_.setLocation(job.getLocation());
            job_.setMinSalary(job.getMinSalary());
            job_.setMaxSalary(job.getMaxSalary());
            jobRepository.save(job_);
            return true;
        }
        return false;
    }
}
