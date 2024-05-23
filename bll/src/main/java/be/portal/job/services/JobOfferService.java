package be.portal.job.services;

import be.portal.job.entities.JobOffer;

public interface JobOfferService {
    JobOffer findById (Long id);
}
