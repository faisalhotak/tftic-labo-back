package be.portal.job.controllers;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.dtos.application.responses.PagedApplicationsResponse;
import be.portal.job.services.IApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/applications")
@PreAuthorize("hasAuthority('SEEKER')")
public class ApplicationController {

    private final IApplicationService applicationService;

    @GetMapping
    public ResponseEntity<PagedApplicationsResponse> getAllApplications(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page
    ) {
        return ResponseEntity.ok(applicationService.getAllBySeeker(params, page));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationByIdAndJobSeekerId(id));
    }

    @PreAuthorize("hasAuthority('ADVERTISER')")
    @GetMapping("/job-offer/{id:^[0-9]+$}")
    public ResponseEntity<List<ApplicationResponse>> getAllApplicationsByJobOfferId(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getAllByJobOfferId(id));
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> addApplication(@RequestBody @Valid ApplicationRequest request) {
        return ResponseEntity.ok(applicationService.addApplication(request));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable Long id,
            @RequestBody @Valid ApplicationUpdateRequest request
    ) {
        return ResponseEntity.ok(applicationService.updateApplication(id, request));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> deleteApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.deleteApplication(id));
    }

    @PatchMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> cancelApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.cancelApplication(id));
    }
}
