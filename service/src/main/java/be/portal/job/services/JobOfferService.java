package be.portal.job.services;

import be.portal.job.dtos.jobOffer.requests.JobOfferPostRequest;
import be.portal.job.dtos.jobOffer.responses.JobOfferResponse;

import java.util.List;

public interface JobOfferService {

    List<JobOfferResponse> getAll();

    JobOfferResponse getJobOfferById(Long id);

    JobOfferResponse deleteJobOffer(Long id);

    JobOfferResponse addJobOffer(JobOfferPostRequest jobOfferPostRequest);
}
