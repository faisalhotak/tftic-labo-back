package be.portal.job.services;

import be.portal.job.dtos.auth.requests.AbstractRegisterRequest;
import be.portal.job.dtos.user.requests.AbstractUserUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.AbstractUserResponse;
import be.portal.job.entities.JobAdvertiser;
import be.portal.job.entities.JobSeeker;

import java.util.List;

public interface UserService {

    List<AbstractUserResponse> getAll();

    List<JobAdvertiserResponse> getAllAdvertisers();

    List<JobSeekerResponse> getAllSeekers();

    AbstractUserResponse getUserById(Long id);

    AbstractUserResponse getUserByEmail(String email);

    JobAdvertiserResponse addAdvertiser(AbstractRegisterRequest<JobAdvertiser> request);

    JobSeekerResponse addSeeker(AbstractRegisterRequest<JobSeeker> request);

    AbstractUserResponse updateAdvertiser(Long id, AbstractUserUpdateRequest<JobAdvertiser> request);

    AbstractUserResponse updateSeeker(Long id, AbstractUserUpdateRequest<JobSeeker> request);

    AbstractUserResponse deleteUser(Long id);
}
