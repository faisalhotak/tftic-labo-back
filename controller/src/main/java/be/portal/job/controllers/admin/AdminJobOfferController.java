package be.portal.job.controllers.admin;

import be.portal.job.dtos.common.IdRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.services.IJobOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/job-offers")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin("*")
public class AdminJobOfferController {

    private final IJobOfferService jobOfferService;

    @PatchMapping("/activate")
    public ResponseEntity<JobOfferResponse> activateJobOffer(@RequestBody @Valid IdRequest request) {
        return ResponseEntity.ok(jobOfferService.triggerActive(request, true));
    }

    @PatchMapping("/deactivate")
    public ResponseEntity<JobOfferResponse> deactivateJobOffer(@RequestBody @Valid IdRequest request) {
        return ResponseEntity.ok(jobOfferService.triggerActive(request, false));
    }
}
