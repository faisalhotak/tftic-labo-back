package be.portal.job.mappers.driving_licence;

import be.portal.job.dtos.driving_licence.requests.DrivingLicenceRequest;
import be.portal.job.dtos.driving_licence.responses.DrivingLicenceResponse;
import be.portal.job.entities.DrivingLicence;
import be.portal.job.entities.JobSeeker;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DrivingLicenceMapper {

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "jobSeeker", target = "jobSeeker")
    DrivingLicence toEntity(DrivingLicenceRequest drivingLicenceRequest, JobSeeker jobSeeker);

    DrivingLicenceResponse fromEntity(DrivingLicence drivingLicence);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "jobSeeker", ignore = true)
    void updateEntityFromRequest(DrivingLicenceRequest requestUpdate, @MappingTarget  DrivingLicence drivingLicence);
}
