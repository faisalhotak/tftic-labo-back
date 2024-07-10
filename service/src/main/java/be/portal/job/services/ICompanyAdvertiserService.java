package be.portal.job.services;

import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;

import java.util.List;

public interface ICompanyAdvertiserService {

    /**
     * Retrieves a list of agents by job advertiser id.
     * @param jobAdvertiserId the unique identifier of the job advertiser.
     * @return a List of CompanyAdvertiser objects representing all agents.
     */
    List<CompanyAdvertiserResponse> getCompanyAdvertiserByJobAdvertiserId(Long jobAdvertiserId);
}
