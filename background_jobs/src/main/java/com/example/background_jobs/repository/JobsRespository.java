package com.example.background_jobs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import com.example.background_jobs.entity.Job_status;
import com.example.background_jobs.entity.Jobs;


public interface JobsRespository extends  JpaRepository<Jobs , UUID > {


     List<Jobs> findByStatus(Job_status status);
}
