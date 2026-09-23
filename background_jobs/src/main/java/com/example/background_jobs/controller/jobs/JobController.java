package com.example.background_jobs.controller.jobs;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.background_jobs.dto.jobs.JobResponse;
import com.example.background_jobs.dto.jobs.CreateJobRequest;
import com.example.background_jobs.service.jobs.JobsService;
import com.example.background_jobs.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping ("/api/jobs")
@Slf4j 
public class JobController {

    public final JobsService jobsService;

    public JobController(JobsService jobsService){
        this.jobsService = jobsService;
    }
    
    @PostMapping 
    public ResponseEntity<ApiResponse<JobResponse>>  createJob(@Valid @RequestBody  CreateJobRequest request){

        log.info("Entered into create createJob");
        JobResponse response = jobsService.createJob(request);
        ApiResponse<JobResponse> result = ApiResponse.<JobResponse>builder().status(201).message("Job created successfully").data(response).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(result);


         
    }

     @GetMapping("/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> getJob( @PathVariable UUID jobId) {

        JobResponse response = jobsService.getJob(jobId);
        ApiResponse<JobResponse> result = ApiResponse.<JobResponse>builder().status(200).message("Job created successfully").data(response).build();
        return ResponseEntity.status(HttpStatus.OK).body(result);


    }

}
