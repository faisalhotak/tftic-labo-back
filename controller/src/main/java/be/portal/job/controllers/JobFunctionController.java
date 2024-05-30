package be.portal.job.controllers;

import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.services.IJobFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/job-functions")
@CrossOrigin("*")
public class JobFunctionController {

    private final IJobFunctionService jobFunctionService;

    @GetMapping
    public ResponseEntity<List<JobFunctionResponse>> getAllJobFunctions() {
        return ResponseEntity.ok(jobFunctionService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobFunctionResponse> getJobFunctionById(@PathVariable Long id) {
        return ResponseEntity.ok(jobFunctionService.getById(id));
    }
}
