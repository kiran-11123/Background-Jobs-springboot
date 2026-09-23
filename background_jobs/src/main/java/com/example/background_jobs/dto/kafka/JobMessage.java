package com.example.background_jobs.dto.kafka;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class JobMessage {

    private UUID jobId;

}
