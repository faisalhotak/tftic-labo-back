package be.portal.job.services;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
import be.portal.job.dtos.common.EmailRequest;
import be.portal.job.dtos.common.IdRequest;
import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;

import java.util.List;

/**
 * The user service. Contains methods for managing users.
 * Used by the ADMIN role.
 */
public interface IUserService {

    /**
     * Get all users.
     * @return A list of all users.
     */
    List<UserResponse> getAll();

    /**
     * Get all advertisers.
     * @return A list of all advertisers.
     */
    List<JobAdvertiserResponse> getAllAdvertisers();

    /**
     * Get all seekers.
     * @return A list of all seekers.
     */
    List<JobSeekerResponse> getAllSeekers();

    /**
     * Get a user by its ID.
     * @param id The ID of the user to get.
     * @return The user with the given ID.
     */
    UserResponse getUserById(Long id);

    /**
     * Get a user by its email.
     * @param email The email of the user to get.
     * @return The user with the given email.
     */
    UserResponse getUserByEmail(String email);

    /**
     * Add an advertiser.
     * @param request The request containing the advertiser's information.
     * @return The added advertiser.
     */
    JobAdvertiserResponse addAdvertiser(JobAdvertiserRegisterRequest request);

    /**
     * Add a seeker.
     * @param request The request containing the seeker's information.
     * @return The added seeker.
     */
    JobSeekerResponse addSeeker(JobSeekerRegisterRequest request);

    /**
     * Update an advertiser.
     * @param id The ID of the advertiser to update.
     * @param request The request containing the new advertiser's information.
     * @return The updated advertiser.
     */
    JobAdvertiserResponse updateAdvertiser(Long id, JobAdvertiserUpdateRequest request);

    /**
     * Update a seeker.
     * @param id The ID of the seeker to update.
     * @param request The request containing the new seeker's information.
     * @return The updated seeker.
     */
    JobSeekerResponse updateSeeker(Long id, JobSeekerUpdateRequest request);

    /**
     * Delete a user.
     * @param id The ID of the user to delete.
     * @return The deleted user.
     */
    UserResponse deleteUser(Long id);

    /**
     * Trigger the lock of a user.
     * @param request The request containing the ID of the user to lock.
     * @param isLocked True to lock the user, false to unlock it.
     * @return The locked/unlocked user.
     */
    UserResponse triggerLock(IdRequest request, boolean isLocked);

    /**
     * Impersonate a user by its ID.
     * @param request The request containing the ID of the user to impersonate.
     * @return The token of the impersonated user.
     */
    UserTokenResponse impersonateUserById(IdRequest request);

    /**
     * Impersonate a user by its email.
     * @param request The request containing the email of the user to impersonate.
     * @return The token of the impersonated user.
     */
    UserTokenResponse impersonateUserByEmail(EmailRequest request);
}
