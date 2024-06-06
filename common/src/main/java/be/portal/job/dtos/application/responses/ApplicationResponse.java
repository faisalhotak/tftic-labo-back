package be.portal.job.dtos.application.responses;

import be.portal.job.enums.ApplicationStatus;

import java.time.LocalDateTime;

public  record ApplicationResponse(
        Long id,
        LocalDateTime applyDate,
        ApplicationStatus applicationStatus
//        JobOfferResponse jobOffer
//        JobSeekerResponse jobSeeker
) { }
