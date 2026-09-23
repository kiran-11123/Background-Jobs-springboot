package com.example.background_jobs.entity;


import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
@Table(name = "jobs")
public class Jobs {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    
    @Column (nullable = false)
    private String type;
   
    @Column (columnDefinition =  "TEXT")
    private String payload;
    
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private  Job_status status;
    
    private int attempts;

    private  int priority;

    private  int maxAttempts;
   
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime scheduledAt;



}
