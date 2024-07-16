package be.portal.job.mappers.application;

import be.portal.job.dtos.application.requests.ApplicationUpdateRequest;
import be.portal.job.dtos.application.responses.ApplicationResponse;
import be.portal.job.dtos.application.responses.PagedApplicationsResponse;
import be.portal.job.entities.Application;
import be.portal.job.entities.JobOffer;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.Role;
import be.portal.job.enums.ApplicationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
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
            LocalDateTime applyDate,
            JobSeeker jobSeeker,
            JobOffer jobOffer,
            ApplicationStatus applicationStatus
    );

    ApplicationResponse fromEntity(Application application);

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "request.applicationStatus", target = "applicationStatus")
    void updateEntityFromRequest(ApplicationUpdateRequest request, @MappingTarget Application application);

    @Mapping(source = "page.content", target = "applications", defaultExpression = "java(java.util.Collections.emptyList())")
    @Mapping(source = "page.size", target = "elementsPerPage")
    PagedApplicationsResponse fromPage(Page<Application> page);

    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
