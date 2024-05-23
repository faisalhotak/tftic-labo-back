package be.portal.job.dtos.driving_licence.requests;

import be.portal.job.entities.DrivingLicence;
import be.portal.job.entities.JobSeeker;
import be.portal.job.enums.DrivingLicenceCategory;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DrivingLicenceAddRequest(

        @NotNull(message = "Driving licence category cannot be null")
        DrivingLicenceCategory drivingLicenceCategory,

        @NotNull(message = "Issue date cannot be null")
        LocalDate issueDate
) {
        public DrivingLicence toEntity(JobSeeker jobSeeker) {
                return new DrivingLicence(
                        drivingLicenceCategory,
                        issueDate,
                        jobSeeker
                );
        }
}
