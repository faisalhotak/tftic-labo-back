package be.portal.job.mappers.company_advertiser;

import be.portal.job.dtos.company_advertiser.requests.CompanyAdvertiserRequest;
import be.portal.job.dtos.company_advertiser.requests.CompanyAdvertiserUpdateRequest;
import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;
import be.portal.job.entities.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CompanyAdvertiserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "company", target = "company")
    @Mapping(source = "jobAdvertiser", target = "jobAdvertiser")
    CompanyAdvertiser toEntity(CompanyAdvertiserRequest request, Company company, JobAdvertiser jobAdvertiser);

    CompanyAdvertiserResponse fromEntity(CompanyAdvertiser companyAdvertiser);

    void updateEntityFromRequest(CompanyAdvertiserUpdateRequest request, @MappingTarget CompanyAdvertiser companyAdvertiser);
}
