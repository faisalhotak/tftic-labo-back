package be.portal.job.dtos.jobOffer.responses;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.contractType.responses.ContractTypeResponse;
import be.portal.job.dtos.jobFunction.responses.JobFunctionResponse;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.entities.JobFunction;
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
        JobAdvertiserResponse agent,
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
                JobAdvertiserResponse.fromEntity(jobOffer.getAgent().getJobAdvertiser()),
                CompanyResponse.fromEntity(jobOffer.getAgent().getCompany()),
                ContractTypeResponse.fromEntity(jobOffer.getContractType()),
                JobFunctionResponse.fromEntity(jobOffer.getJobFunction())
        );
    }
}
