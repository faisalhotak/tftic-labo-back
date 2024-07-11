package be.portal.job.mappers.job_offer;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.dtos.job_offer.responses.PagedJobOfferResponse;
import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.entities.ContractType;
import be.portal.job.entities.JobFunction;
import be.portal.job.entities.JobOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "request.description", target = "description")
    @Mapping(source = "request.isActive", target = "active")
    @Mapping(source = "agent", target = "agent")
    @Mapping(source = "contractType", target = "contractType")
    @Mapping(source = "jobFunction", target = "jobFunction")
    JobOffer toEntity(
            JobOfferRequest request,
            CompanyAdvertiser agent,
            ContractType contractType,
            JobFunction jobFunction
    );

    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "agent.company.active", target = "agent.company.isActive")
    JobOfferResponse fromEntity(JobOffer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "agent", ignore = true)
    @Mapping(source = "request.description", target = "description")
    @Mapping(source = "request.isActive", target = "active")
    void updateEntityFromRequest(
            JobOfferRequest request,
            ContractType contractType,
            JobFunction jobFunction,
            @MappingTarget JobOffer jobOffer
    );

    @Mapping(source = "page.content", target = "jobOffers")
    @Mapping(source = "page.size", target = "elementsPerPage")
    PagedJobOfferResponse fromPage(Page<JobOffer> page);
}
