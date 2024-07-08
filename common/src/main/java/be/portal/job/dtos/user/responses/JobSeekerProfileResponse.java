package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;
import be.portal.job.enums.Gender;

import java.time.LocalDate;

public record JobSeekerProfileResponse(
        Long id,
        String email,
        String firstname,
        String lastname,
        String phoneNumber,
        String contactEmail,
        Address address,
        LocalDate birthDate,
        Gender gender
) { }
