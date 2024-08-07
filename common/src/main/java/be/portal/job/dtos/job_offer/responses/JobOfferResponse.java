package be.portal.job.dtos.job_offer.responses;

import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;
import be.portal.job.entities.ZipCity;

import java.time.LocalDateTime;

public record JobOfferResponse(
        Long id,
        String description,
        Double annualGrossSalaryMin,
        Double annualGrossSalaryMax,
        LocalDateTime createdAt,
        Integer activeDays,
        LocalDateTime expiringDate,
        ZipCity zipCity,
        Boolean isActive,
        CompanyAdvertiserResponse agent,
        ContractTypeResponse contractType,
        JobFunctionResponse jobFunction
) { }
