package be.portal.job.services;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;

/**
 * Service interface for profile management in the system.
 */
public interface IProfileService {

        /**
        * Disables the profile of the current user.
        * @return the user which was disabled.
        */
        UserResponse disableProfile();

        /**
         * Deletes the profile of the current user.
         * @return the user which was disabled.
         */
        UserResponse deleteProfile();
        /**
         * Updates the profile of the current jobA
         * @param jobSeekerUpdateRequest the new information for the job seeker.
         * @return a message indicating that the profile has been updated.
         */
        JobSeekerResponse updateJobSeekerProfile(JobSeekerUpdateRequest jobSeekerUpdateRequest);

        /**
         * Updates the profile of the current jobAdvertiser.
         * @param jobAdvertiserUpdateRequest the new information for the job advertiser.
         * @return a message indicating that the profile has been updated.
         */
        JobAdvertiserResponse updateJobAdvertiserProfile(JobAdvertiserUpdateRequest jobAdvertiserUpdateRequest);

}
