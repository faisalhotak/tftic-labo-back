package be.portal.job.services;

import be.portal.job.dtos.experience_detail.requests.ExperienceDetailAddRequest;
import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;

import java.util.List;

/**
 * Service interface for experience détails management in the system.
 * Provides basic CRUD operations for experience détails.
 */
public interface IExperienceDetailService {

    /**
     * Retrieve all experience détails from the system.
     * @return a list of all experience détails of a seeker.
     */
    List<ExperienceDetailResponse> getAllByCurrentSeeker();


    /**
     * Add a new experience détail to the system.
     * @param experienceDetailRequest the experience détail to add.
     * @return the added experience détail.
     */
    ExperienceDetailResponse addExperienceDetail(ExperienceDetailAddRequest experienceDetailRequest);
}
