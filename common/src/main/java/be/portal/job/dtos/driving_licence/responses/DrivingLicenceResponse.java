package be.portal.job.dtos.driving_licence.responses;

import be.portal.job.enums.DrivingLicenceCategory;

import java.time.LocalDate;

public record DrivingLicenceResponse(
        Long id,
        DrivingLicenceCategory drivingLicenceCategory,
        LocalDate issueDate
) { }
