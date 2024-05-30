package be.portal.job.dtos.user.responses;

public record UserShortResponse(
        String email,
        String firstname,
        String lastname,
        String phoneNumber,
        String contactEmail
) { }
