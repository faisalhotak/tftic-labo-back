package be.portal.job.controllers;

import be.portal.job.dtos.jobOffer.requests.JobOfferPostRequest;
import be.portal.job.dtos.jobOffer.responses.JobOfferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import be.portal.job.services.IJobOfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-offers")
@PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
public class JobOfferController {

    private final IJobOfferService jobOfferService;

    @GetMapping
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffers() {
        return ResponseEntity.ok(jobOfferService.getAll());
    }
    
    @GetMapping("/agent/{id:^[0-9]+$}")
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffersByJobAdvertiser(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getAllByAgent(id));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> getJobOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }

    @PostMapping()
    public ResponseEntity<JobOfferResponse> addJobOffer(@RequestBody JobOfferPostRequest jobOfferPostRequest) {
        return ResponseEntity.ok(jobOfferService.addJobOffer(jobOfferPostRequest));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> updateJobOffer(@PathVariable Long id, @RequestBody JobOfferPostRequest jobOfferPostRequest) {
        return ResponseEntity.ok(jobOfferService.updateJobOffer(id, jobOfferPostRequest));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<JobOfferResponse> deleteJobOffer(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.deleteJobOffer(id));
    }
}
