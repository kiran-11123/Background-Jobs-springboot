package com.example.background_jobs.service.kafka;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.background_jobs.dto.kafka.JobMessage;

@Service 
public class JobProducer {

    private  static  final String TOPIC = "job-topic";

    private  final KafkaTemplate<String,JobMessage> kafkaTemplate;

    public JobProducer(KafkaTemplate<String,JobMessage> kafkaTemplate ){
         this.kafkaTemplate = kafkaTemplate;
    }

    public void sendJob(UUID jobId){
         JobMessage message = new JobMessage(jobId);

         kafkaTemplate.send(
            TOPIC,
            jobId.toString(),
            message
         );
    }

}
