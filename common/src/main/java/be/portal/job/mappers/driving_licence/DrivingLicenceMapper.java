package be.portal.job.mappers.driving_licence;

import be.portal.job.dtos.driving_licence.requests.DrivingLicenceAddRequest;
import be.portal.job.dtos.driving_licence.requests.DrivingLicenceUpdateRequest;
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
    DrivingLicence toEntity(DrivingLicenceAddRequest drivingLicenceAddRequest, JobSeeker jobSeeker);

    DrivingLicenceResponse fromEntity(DrivingLicence drivingLicence);

    void updateEntityFromRequest(DrivingLicenceUpdateRequest requestUpdate, @MappingTarget  DrivingLicence drivingLicence);
}
