package com.example.background_jobs.dto.jobs;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.background_jobs.entity.Job_status;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder 
public class JobResponse {

    private  UUID id;

    private  String type;
    private  String payload;

    private  Job_status status;
     private int attempts;
   
     private  int priority;
    private int maxAttempts;

    private LocalDateTime createdAt;
    private LocalDateTime scheduledAt;

    private LocalDateTime updatedAt;


}
