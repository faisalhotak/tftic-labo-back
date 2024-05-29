package be.portal.job.controllers;

import be.portal.job.dtos.job_function.requests.JobFunctionRequest;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.services.IJobFunctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(jobFunctionService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobFunctionResponse> getJobFunctionById(@PathVariable Long id) {
        return ResponseEntity.ok(jobFunctionService.getById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<JobFunctionResponse> addJobFunction(@RequestBody @Valid JobFunctionRequest request) {
        return ResponseEntity.ok(jobFunctionService.add(request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobFunctionResponse> updateJobFunction(
            @PathVariable Long id,
            @RequestBody @Valid JobFunctionRequest request
    ) {
        return ResponseEntity.ok(jobFunctionService.update(id, request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobFunctionResponse> deleteJobFunction(@PathVariable Long id) {
        return ResponseEntity.ok(jobFunctionService.delete(id));
    }
}
