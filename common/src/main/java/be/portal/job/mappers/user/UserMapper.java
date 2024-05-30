package be.portal.job.mappers.user;

import be.portal.job.dtos.auth.requests.AbstractRegisterRequest;
import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.user.requests.UserUpdateRequest;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.entities.*;
import be.portal.job.exceptions.auth.InvalidUserTypeException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Map a JobAdvertiserRegisterRequest to a JobAdvertiser entity.
     * @param request the request to map
     * @param roles the roles to assign to the user
     * @return the job advertiser entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "request.street", target = "address.street")
    @Mapping(source = "request.city", target = "address.city")
    @Mapping(source = "request.zip", target = "address.zip")
    @Mapping(source = "request.country", target = "address.country")
    JobAdvertiser toJobAdvertiser(JobAdvertiserRegisterRequest request, Set<Role> roles);

    /**
     * Map a JobSeekerRegisterRequest to a JobSeeker entity.
     * @param request the request to map
     * @param roles the roles to assign to the user
     * @return the job seeker entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "request.street", target = "address.street")
    @Mapping(source = "request.city", target = "address.city")
    @Mapping(source = "request.zip", target = "address.zip")
    @Mapping(source = "request.country", target = "address.country")
    JobSeeker toJobSeeker(JobSeekerRegisterRequest request, Set<Role> roles);

    /**
     * Map an AbstractRegisterRequest to a User entity.
     * @param request the request to map
     * @param roles the roles to assign to the user
     * @return the user entity
     */
    default User toEntity(AbstractRegisterRequest request, Set<Role> roles) {
        if (request instanceof JobAdvertiserRegisterRequest jobAdvertiserRegisterRequest) {
            return toJobAdvertiser(jobAdvertiserRegisterRequest, roles);
        }

        if (request instanceof JobSeekerRegisterRequest jobSeekerRegisterRequest) {
            return toJobSeeker(jobSeekerRegisterRequest, roles);
        }

        throw new InvalidUserTypeException();
    }

    /**
     * Map a set of roles to a set of role names.
     * @param roles the set of roles to map
     * @return the set of role names
     */
    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    /**
     * Update a user entity with the data from a UserUpdateRequest.
     * @param request the request to update the user with
     * @param user the user to update
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "zip", target = "address.zip")
    @Mapping(source = "country", target = "address.country")
    void updateEntityFromRequest(UserUpdateRequest request, @MappingTarget User user);

    /**
     * Map a User entity to a UserResponse.
     * @param user the user to map
     * @return the user response
     */
    UserResponse fromUser(User user);

    /**
     * Map a JobAdvertiser entity to a JobAdvertiserResponse.
     * @param jobAdvertiser the advertiser to map
     * @return the job advertiser response
     */
    JobAdvertiserResponse fromJobAdvertiser(JobAdvertiser jobAdvertiser);

    /**
     * Map a JobSeeker entity to a JobSeekerResponse.
     * @param jobSeeker the seeker to map
     * @return the job seeker response
     */
    JobSeekerResponse fromJobSeeker(JobSeeker jobSeeker);
}
