package be.portal.job.mappers.job_offer;

import be.portal.job.dtos.job_offer.requests.JobOfferRequest;
import be.portal.job.dtos.job_offer.responses.JobOfferResponse;
import be.portal.job.entities.JobOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    JobOffer toEntity(JobOfferRequest request);

    JobOfferResponse fromEntity(JobOffer entity);

    void updateEntityFromRequest(JobOfferRequest request, @MappingTarget JobOffer jobOffer);
}
