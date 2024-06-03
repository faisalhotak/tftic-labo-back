package be.portal.job.dtos.driving_licence.responses;

import be.portal.job.enums.DrivingLicenceCategory;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DrivingLicenceResponse(

        @NotNull(message = "Driving licence category cannot be null")
        DrivingLicenceCategory drivingLicenceCategory,

        @NotNull(message = "Issue date cannot be null")
        LocalDate issueDate
) { }
