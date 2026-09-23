package com.example.background_jobs.service.jobs;
import com.example.background_jobs.entity.Jobs;
import com.example.background_jobs.exception.jobs.JobNotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.example.background_jobs.dto.jobs.CreateJobRequest;
import com.example.background_jobs.dto.jobs.JobResponse;
import com.example.background_jobs.entity.Job_status;

import com.example.background_jobs.repository.JobsRespository;
import lombok.extern.slf4j.Slf4j;
import  com.example.background_jobs.service.kafka.JobProducer;


@Service 
@Slf4j 
public class JobsService {

    private JobsRespository jobsRespository;
    private JobProducer jobProducer;

    public  JobsService(JobsRespository jobsRespository , JobProducer jobProducer){
        this.jobsRespository = jobsRespository;
        this.jobProducer = jobProducer;
    }


    public JobResponse convertToResponse(Jobs job){
         
        return JobResponse.builder().id(job.getId()).type(job.getType()).payload(job.getPayload()).status(job.getStatus()).attempts(job.getAttempts()).priority(job.getPriority()).createdAt(job.getCreatedAt()).maxAttempts(job.getMaxAttempts()).scheduledAt(job.getScheduledAt()).updatedAt(job.getUpdatedAt()).build();


    }
    

    public JobResponse createJob(CreateJobRequest request){
           try{

                Jobs job = Jobs.builder().type(request.getType())
                           .payload(request.getPayload())
                           .status(Job_status.PENDING)
                            .priority(request.getPriority())
                             .attempts(0)
                           .maxAttempts(3)
                           .createdAt(LocalDateTime.now())
                           .updatedAt(LocalDateTime.now())
                           .build();

            Jobs savedJob = jobsRespository.save(job);
            jobProducer.sendJob(savedJob.getId());

            log.info("Job with Job Id {} added into kafka" , savedJob.getId());
            log.info("Job created successfully. Job ID: {}", savedJob.getId());

            return convertToResponse(savedJob);

           }
           catch(Exception e){

              log.error("ERROR WHILE CREATING JOB", e);

        throw e;
           }
    }

    public  JobResponse getJob(UUID jobId){

        try{

            Jobs job = jobsRespository.findById(jobId).orElseThrow(()->
                new JobNotFoundException("Job not found with Id : " + jobId)
            );

            return convertToResponse(job);
        }
        catch(JobNotFoundException e){
            throw e;
        }
        catch(Exception e){

            log.error("Error while fetching job: {}", jobId, e);

            throw new RuntimeException("Failed to fetch job");
        }
         
    }


    public void processJob(UUID jobId){
           
      try{

      
        Jobs job = jobsRespository.findById(jobId).orElseThrow(()->new JobNotFoundException("Job Not found" + jobId));
        
         log.info("Processing job: {}", jobId);

        job.setStatus(Job_status.PROCESSING);
        job.setAttempts(job.getAttempts() + 1);
        job.setUpdatedAt(LocalDateTime.now());
        jobsRespository.save(job);


        try{

            Thread.sleep(10);

            job.setStatus(Job_status.COMPLETED);
            job.setUpdatedAt(LocalDateTime.now());

              log.info(
                "Job completed successfully. Job ID: {}",
                jobId
        );



        }
        catch(Exception e){

             log.error(
                "Job processing failed. Job ID: {}",
                jobId,
                e
        );
            
            job.setStatus(Job_status.FAILED);
            job.setUpdatedAt(LocalDateTime.now());

        }

        

      }
      catch(JobNotFoundException e){
        throw e;
      }
      catch(Exception e){
         log.info("Error while processing the job");
         throw e;
      }

    }
}
