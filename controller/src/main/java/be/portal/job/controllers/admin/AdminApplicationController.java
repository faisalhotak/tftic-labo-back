package be.portal.job.controllers.admin;

import be.portal.job.dtos.application.requests.ApplicationStatusRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.dtos.application.responses.PagedApplicationsResponse;
import be.portal.job.services.IApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/applications")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminApplicationController {

    private final IApplicationService applicationService;

    @GetMapping
    public ResponseEntity<PagedApplicationsResponse> getAllApplications(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page
    ) {
        return ResponseEntity.ok(applicationService.getAll(params, page));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    @PatchMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ApplicationResponse> triggerApplicationStatus(
            @PathVariable Long id,
            @RequestBody @Valid ApplicationStatusRequest request
    ) {
        return ResponseEntity.ok(applicationService.triggerApplicationStatus(id, request));
    }
}
