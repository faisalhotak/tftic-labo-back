package be.portal.job.controllers.admin;

import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.services.IJobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/job-offers")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminJobOfferController {

    private final IJobOfferService jobOfferService;

    @PatchMapping("/{id:^[0-9]+$}/activate")
    public ResponseEntity<JobOfferResponse> activateJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.triggerActive(id, true));
    }

    @PatchMapping("/{id:^[0-9]+$}/deactivate")
    public ResponseEntity<JobOfferResponse> deactivateJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.triggerActive(id, false));
    }
}
