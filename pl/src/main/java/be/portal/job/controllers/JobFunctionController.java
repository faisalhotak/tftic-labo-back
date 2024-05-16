package be.portal.job.controllers;

import be.portal.job.models.dtos.JobFunctionDTO;
import be.portal.job.models.forms.JobFunctionForm;
import be.portal.job.services.IJobFunctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobfunction")
@CrossOrigin("*")
public class JobFunctionController {

    public final IJobFunctionService jobFunctionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<JobFunctionDTO>> getAllJobFunctions() {
        List<JobFunctionDTO> jobFunctionDTOS = jobFunctionService.getJobFunction()
                .stream()
                .map(JobFunctionDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jobFunctionDTOS);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<JobFunctionDTO> getJobFunctionById(@PathVariable Long id) {
        JobFunctionDTO jobFunctionDTO = JobFunctionDTO.fromEntity(jobFunctionService.getJobFunctionById(id));

        return ResponseEntity.ok(jobFunctionDTO);
    }
    
    @PostMapping("/post")
    public ResponseEntity<JobFunctionDTO> addJobFunction(
            @Valid @RequestBody JobFunctionForm jobFunctionForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        JobFunctionDTO newJobFunction = JobFunctionDTO.fromEntity(jobFunctionService.addJobFunction(jobFunctionForm.toEntity()));

        return ResponseEntity.ok(newJobFunction);
    }
}
