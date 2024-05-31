package be.portal.job.controllers;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.exceptions.company.CompanyNotVerifiedOrActiveException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import be.portal.job.services.IJobOfferService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-offers")
public class JobOfferController {

    private final IJobOfferService jobOfferService;

    @GetMapping
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffers(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(jobOfferService.getAll(params));
    }
    
    @GetMapping("/agent/{id:^[0-9]+$}")
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffersByJobAdvertiser(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getAllByAgent(id));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> getJobOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @PostMapping()
    public ResponseEntity<?> addJobOffer(@RequestBody @Valid JobOfferRequest jobOfferRequest) {
        try {
            JobOfferResponse jobOfferResponse = jobOfferService.addJobOffer(jobOfferRequest);
            return ResponseEntity.ok(jobOfferResponse);
        } catch (CompanyNotVerifiedOrActiveException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> updateJobOffer(
            @PathVariable Long id,
            @RequestBody @Valid JobOfferRequest jobOfferRequest
    ) {
        return ResponseEntity.ok(jobOfferService.updateJobOffer(id, jobOfferRequest));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> deleteJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.deleteJobOffer(id));
    }
}
