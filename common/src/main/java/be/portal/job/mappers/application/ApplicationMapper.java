package be.portal.job.mappers.application;

import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.Role;
import be.portal.job.enums.ApplicationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "jobSeeker", target = "jobSeeker")
    @Mapping(source = "jobOffer", target = "jobOffer")
    @Mapping(source = "applicationStatus", target = "applicationStatus")
    Application toEntity(
            JobSeeker jobSeeker,
            JobOffer jobOffer,
            ApplicationStatus applicationStatus
    );

    ApplicationResponse fromEntity(Application application);

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobSeeker",ignore = true)
    @Mapping(target = "jobOffer", ignore = true)
    @Mapping(target = "applicationStatus", ignore = true)
    void updateEntityFromRequest(ApplicationUpdateRequest request, @MappingTarget Application application);

    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
