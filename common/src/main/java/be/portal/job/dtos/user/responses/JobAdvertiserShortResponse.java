package be.portal.job.dtos.user.responses;

import be.portal.job.entities.JobAdvertiser;

public record JobAdvertiserShortResponse(
        String email,
        String firstname,
        String lastname,
        String phoneNumber,
        String contactEmail
) {
    public static JobAdvertiserShortResponse fromEntity(JobAdvertiser jobAdvertiser) {
        return new JobAdvertiserShortResponse(
                jobAdvertiser.getEmail(),
                jobAdvertiser.getFirstname(),
                jobAdvertiser.getLastname(),
                jobAdvertiser.getPhoneNumber(),
                jobAdvertiser.getContactEmail()
        );
    }
}
