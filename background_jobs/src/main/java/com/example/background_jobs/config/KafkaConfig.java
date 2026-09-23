package com.example.background_jobs.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.background_jobs.dto.kafka.JobMessage;

@Configuration
public class KafkaConfig {
     @Bean
    public NewTopic jobTopic() {

        return new NewTopic(
                "job-topic",
                3,
                (short) 1
        );
    }

  
}
