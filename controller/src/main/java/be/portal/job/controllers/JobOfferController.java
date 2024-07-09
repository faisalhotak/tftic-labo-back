package be.portal.job.controllers;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.requests.JobOfferTransferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import be.portal.job.services.IJobOfferService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/job-offers")
public class JobOfferController {

    private final IJobOfferService jobOfferService;

    @GetMapping
    public ResponseEntity<Page<JobOfferResponse>> getAllJobOffers(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page
    ) {
        return ResponseEntity.ok(jobOfferService.getAll(params, page));
    }

    @GetMapping("/agents/{id:^[0-9]+$}")
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffersByAgent(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getAllByAgent(id));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> getJobOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getAllLocations() {
        return ResponseEntity.ok(jobOfferService.getAllLocations());
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @GetMapping("/companies/{id}")
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffersByCompanyId(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getAllJobOffersByCompany(id));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @PostMapping()
    public ResponseEntity<JobOfferResponse> addJobOffer(@RequestBody @Valid JobOfferRequest jobOfferRequest) {
            return ResponseEntity.ok(jobOfferService.addJobOffer(jobOfferRequest));
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
    @PatchMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> transferJobOffer(
            @PathVariable Long id,
            @RequestBody @Valid JobOfferTransferRequest jobOfferTransferRequest
    ) {
        return ResponseEntity.ok(jobOfferService.transferJobOffer(id, jobOfferTransferRequest));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> deleteJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.deleteJobOffer(id));
    }
}
