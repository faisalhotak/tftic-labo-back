package be.portal.job.services;

import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.dtos.company_advertiser.requests.CompanyAdvertiserRequest;
import be.portal.job.dtos.company_advertiser.requests.CompanyAdvertiserUpdateRequest;
import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;

import java.util.List;

/**
 * Service interface for job offer management in the system.
 */
public interface ICompanyService {

    /**
     * Retrieves a list of all companies.
     * @return a list of all companies.
     */
    List<CompanyResponse> getAll();

    /**
     * Retrieves a company by its unique identifier.
     * @param id the identifier of the company to search for.
     * @return the company corresponding to the provided identifier, or null if not found.
     */
    CompanyResponse getCompanyById(Long id);

    /**
     * Creates a new company in the system.
     * @param company the company to add.
     * @return The newly created job function.
     */
    CompanyResponse addCompany(CompanyRequest company);

    /**
     * Updates an existing company.
     * @param id the identifier of the company to update.
     * @param company the new information for the company.
     * @return the updated company, or null if the company does not exist.
     */
    CompanyResponse updateCompany(Long id, CompanyRequest company);

    /**
     * Deletes a company by its identifier.
     * @param id the identifier of the company to delete.
     */
    CompanyResponse deleteCompany(Long id);

    /**
     * Adds an advertiser to a company.
     *
     * @param request The request object containing the details of the advertiser to be added.
     * @return A response object containing the details of the added advertiser.
     */
    CompanyAdvertiserResponse addAdvertiserToCompany(Long companyId, CompanyAdvertiserRequest request);

    /**
     * Updates an advertiser for a company.
     * @param request The request object containing the updated details of the advertiser.
     * @return A response object containing the details of the updated advertiser.
     */
    CompanyAdvertiserResponse updateCompanyAdvertiser(Long agentId, CompanyAdvertiserUpdateRequest request);

    /**
     * Deletes an advertiser from a company.
     * @param agentId The ID of the user who is performing the operation.
     * @return A response object indicating the result of the deletion operation.
     */
    CompanyAdvertiserResponse deleteCompanyAdvertiser(Long agentId);

}
