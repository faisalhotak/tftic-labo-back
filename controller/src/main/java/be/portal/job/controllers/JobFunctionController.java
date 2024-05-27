package be.portal.job.controllers;

import be.portal.job.dtos.job_function.requests.JobFunctionRequest;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.services.IJobFunctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-functions")
@CrossOrigin("*")
public class JobFunctionController {

    public final IJobFunctionService jobFunctionService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<JobFunctionResponse>> getAllJobFunctions() {
        List<JobFunctionResponse> jobFunctionDTOS = jobFunctionService.getJobFunction()
                .stream()
                .map(JobFunctionResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(jobFunctionDTOS);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<JobFunctionResponse> getJobFunctionById(@PathVariable Long id) {
        JobFunctionResponse jobFunctionDTO = JobFunctionResponse.fromEntity(jobFunctionService.getJobFunctionById(id));

        return ResponseEntity.ok(jobFunctionDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<JobFunctionResponse> addJobFunction(
            @Valid @RequestBody JobFunctionRequest jobFunctionForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        JobFunctionResponse newJobFunction = JobFunctionResponse.fromEntity(jobFunctionService.addJobFunction(jobFunctionForm.toEntity()));

        return ResponseEntity.ok(newJobFunction);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<JobFunctionResponse> updateJobFunction(@PathVariable Long id, @Valid @RequestBody JobFunctionRequest jobFunctionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        JobFunctionResponse jobFunctionDTO = JobFunctionResponse.fromEntity(jobFunctionService.updateJobFunction(id, jobFunctionForm.toEntity()));

        return ResponseEntity.ok(jobFunctionDTO);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<JobFunctionResponse> deleteJobFunction(@PathVariable Long id) {
        jobFunctionService.deleteJobFunction(id);

        return ResponseEntity.noContent().build();
    }
}
