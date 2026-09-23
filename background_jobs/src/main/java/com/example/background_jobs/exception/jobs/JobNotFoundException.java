package com.example.background_jobs.exception.jobs;

public class JobNotFoundException extends  RuntimeException{

    public  JobNotFoundException(String message){
         super(message);
    }

}
