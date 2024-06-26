package be.portal.job.services;

import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.entities.User;

/**
 * Service interface for profile management in the system.
 */
public interface IProfileService {

        /**
         * Disables the profile of the current user.
         * @return the user which was disabled.
         */
        UserResponse disableSelf();

        /**
         * Deletes the profile of the current user.
         * @return the user which was disabled.
         */
        UserResponse deleteSelf();

        /**
        * Disables the profile of the current user.
        * @param user the user to disable.
        * @return the user which was disabled.
        */
        UserResponse disableProfile(User user);

        /**
         * Deletes the profile of the current user.
         * @param user the user to delete.
         * @return the user which was disabled.
         */
        UserResponse deleteProfile(User user);

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

        /**
         * Deletes a user as an admin.
         * @param id the id of the user to delete.
         * @return the user which was deleted.
         */
        UserResponse deleteUserAsAdmin(Long id);
}
