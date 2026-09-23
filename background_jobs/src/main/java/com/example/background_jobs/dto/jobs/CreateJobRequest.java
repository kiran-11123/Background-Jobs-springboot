package com.example.background_jobs.dto.jobs;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data 
@AllArgsConstructor 
@NoArgsConstructor  
public class CreateJobRequest {

    
    @NotBlank (message = "Job Type is required")
    private  String type;

     @NotBlank(message = "Payload is required")
    private String payload;

    
    @Min (value = 1 , message = "Priority must be atleast 1")
    @Max (value =  10 , message =  "Priority should not be greater than 10")
    private int priority = 5;

}
