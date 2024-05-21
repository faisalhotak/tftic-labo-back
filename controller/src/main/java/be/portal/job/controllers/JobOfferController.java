package be.portal.job.controllers;

import be.portal.job.dtos.jobOffer.requests.JobOfferPostRequest;
import be.portal.job.dtos.jobOffer.responses.JobOfferResponse;
import be.portal.job.dtos.user.responses.AbstractUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import be.portal.job.services.JobOfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-offers")
@PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @GetMapping
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffers() {
        return ResponseEntity.ok(jobOfferService.getAll());
    }


    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> getJobOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> deleteJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.deleteJobOffer(id));
    }

    @PostMapping()
    public ResponseEntity<JobOfferResponse> addJobOffer(@RequestBody JobOfferPostRequest jobOfferPostRequest) {
        return ResponseEntity.ok(jobOfferService.addJobOffer(jobOfferPostRequest));
    }
}
