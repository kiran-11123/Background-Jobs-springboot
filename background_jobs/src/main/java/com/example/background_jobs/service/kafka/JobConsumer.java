package com.example.background_jobs.service.kafka;

import com.example.background_jobs.dto.kafka.JobMessage;
import com.example.background_jobs.service.jobs.JobsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobConsumer {

    private final JobsService jobsService;

    public JobConsumer(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @KafkaListener(
            topics = "job-topic",
            groupId = "job-worker-group"
    )
    public void consume(JobMessage message) {

        log.info(
                "Received job from Kafka. Job ID: {}",
                message.getJobId()
        );

        jobsService.processJob(message.getJobId());
    }
}