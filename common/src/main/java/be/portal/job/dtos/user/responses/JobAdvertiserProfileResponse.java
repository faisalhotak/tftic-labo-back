package be.portal.job.dtos.user.responses;

import be.portal.job.entities.Address;

public record JobAdvertiserProfileResponse(
        Long id,
        String email,
        String firstname,
        String lastname,
        String phoneNumber,
        String contactEmail,
        Address address
) { }
