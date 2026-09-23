package com.example.background_jobs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.background_jobs.exception.jobs.JobNotFoundException;
import com.example.background_jobs.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice 
@Slf4j 
public class GlobalExceptionHandler {


        @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception e) {

        log.error(
                "Unexpected error occurred",
                e
        );

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Something went wrong")
                        .data(null)
                        .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
    
    @ExceptionHandler(JobNotFoundException.class)
    public  ResponseEntity<ApiResponse<Void>> handleJobNotFoundExecption(Exception e){
         
           log.error(
            "Job Not found",
            e.getMessage()
    );

       ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Job Not found")
                        .data(null)
                        .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    


}
