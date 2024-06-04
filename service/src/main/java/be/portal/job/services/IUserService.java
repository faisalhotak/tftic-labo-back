package be.portal.job.services;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.auth.responses.UserTokenResponse;
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
     * Trigger the lock of a user.
     * @param id The ID of the user to lock.
     * @param isLocked Whether to lock or unlock the user.
     * @return The locked user.
     */
    UserResponse triggerLock(Long id, boolean isLocked);

    /**
     * Trigger the enable of a user.
     * @param id The ID of the user to enable.
     * @param isEnabled Whether to enable or disable the user.
     * @return The enabled user.
     */
    UserResponse triggerEnable(Long id, boolean isEnabled);

    /**
     * Impersonate a user by its ID.
     * @param id The ID of the user to impersonate.
     * @return The token of the impersonated user.
     */
    UserTokenResponse impersonateUserById(Long id);

    /**
     * Impersonate a user by its email.
     * @param email The email of the user to impersonate.
     * @return The token of the impersonated user.
     */
    UserTokenResponse impersonateUserByEmail(String email);
}
