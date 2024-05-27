package be.portal.job.dtos.job_offer.responses;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.dtos.user.responses.JobAdvertiserShortResponse;
import be.portal.job.entities.JobOffer;

import java.time.LocalDateTime;

public record JobOfferResponse(

        Long id,
        String description,
        Double annualGrossSalaryMin,
        Double annualGrossSalaryMax,
        LocalDateTime publishing,
        Integer activeDays,
        LocalDateTime expiringDate,
        boolean isActive,
        JobAdvertiserShortResponse agent,
        CompanyResponse publishingCompany,
        ContractTypeResponse contractType,
        JobFunctionResponse jobFunction

) {

    public static JobOfferResponse fromEntity(JobOffer jobOffer) {
        return new JobOfferResponse(
                jobOffer.getId(),
                jobOffer.getDescription(),
                jobOffer.getAnnualGrossSalaryMin(),
                jobOffer.getAnnualGrossSalaryMax(),
                jobOffer.getPublishedDate(),
                jobOffer.getActiveDays(),
                jobOffer.getExpiringDate(),
                jobOffer.isActive(),
                JobAdvertiserShortResponse.fromEntity(jobOffer.getAgent().getJobAdvertiser()),
                CompanyResponse.fromEntity(jobOffer.getAgent().getCompany()),
                ContractTypeResponse.fromEntity(jobOffer.getContractType()),
                JobFunctionResponse.fromEntity(jobOffer.getJobFunction())
        );
    }
}
