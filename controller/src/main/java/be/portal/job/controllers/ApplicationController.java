package be.portal.job.controllers;

import be.portal.job.dtos.application.requests.ApplicationRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.entities.Application;
import be.portal.job.services.IApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/application")
@CrossOrigin("*")
public class ApplicationController {

    public final IApplicationService applicationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplication() {
        return ResponseEntity.ok(applicationService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @PostMapping()
    public ResponseEntity<ApplicationResponse> addApplication(@RequestBody @Valid ApplicationRequest applicationRequest) {
        return ResponseEntity.ok(applicationService.addApplication(applicationRequest));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable Long id,
            @RequestBody @Valid ApplicationRequest applicationRequest
    ) {
        return ResponseEntity.ok(applicationService.updateApplication(id, applicationRequest));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> deleteApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.deleteApplication(id));
    }
}
