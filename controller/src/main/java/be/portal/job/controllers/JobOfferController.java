package be.portal.job.controllers;

import be.portal.job.dtos.jobOffer.responses.JobOfferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.portal.job.services.JobOfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-offers")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffers() {
        return ResponseEntity.ok(jobOfferService.getAll());
    }
}
