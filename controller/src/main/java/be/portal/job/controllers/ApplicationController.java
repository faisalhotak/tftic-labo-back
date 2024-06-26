package be.portal.job.controllers;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.services.IApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/applications")
@PreAuthorize("hasAuthority('SEEKER')")
public class ApplicationController {

    private final IApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplication() {
        return ResponseEntity.ok(applicationService.getAllBySeeker());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationByIdAndJobSeekerId(id));
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
