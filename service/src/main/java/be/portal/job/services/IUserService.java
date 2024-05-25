package be.portal.job.services;

import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.user.requests.JobAdvertiserUpdateRequest;
import be.portal.job.dtos.user.requests.JobSeekerUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;

import java.util.List;

public interface IUserService {

    List<UserResponse> getAll();

    List<JobAdvertiserResponse> getAllAdvertisers();

    List<JobSeekerResponse> getAllSeekers();

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);

    JobAdvertiserResponse addAdvertiser(JobAdvertiserRegisterRequest request);

    JobSeekerResponse addSeeker(JobSeekerRegisterRequest request);

    JobAdvertiserResponse updateAdvertiser(Long id, JobAdvertiserUpdateRequest request);

    JobSeekerResponse updateSeeker(Long id, JobSeekerUpdateRequest request);

    UserResponse deleteUser(Long id);
}
