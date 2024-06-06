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
     * @param companyId the ID of the company to which the advertiser is to be added
     * @param request the request object containing the details of the advertiser to be added
     * @return a response object containing the details of the added advertiser
     */
    CompanyAdvertiserResponse addAdvertiserToCompany(Long companyId, CompanyAdvertiserRequest request);

    /**
     * Updates the details of a company advertiser.
     * @param agentId the ID of the advertiser to be updated
     * @param request the request object containing the updated details of the advertiser
     * @return a response object containing the updated details of the advertiser
     */
    CompanyAdvertiserResponse updateCompanyAdvertiser(Long agentId, CompanyAdvertiserUpdateRequest request);

    /**
     * Deletes a company advertiser.
     * @param agentId the ID of the advertiser to be deleted
     * @return a response object confirming the deletion of the advertiser
     */
    CompanyAdvertiserResponse deleteCompanyAdvertiser(Long agentId);


    /**
     * Adds a company as an admin.
     * @param userId the id of the user.
     * @param companyRequest the company request.
     * @return the company response.
     */
    CompanyResponse addCompanyAsAdmin(Long userId, CompanyRequest companyRequest);

    /**
     * Updates a company as an admin.
     * @param id the id of the company.
     * @param companyRequest the company request.
     * @return the company response.
     */
    CompanyResponse updateCompanyAsAdmin(Long id, CompanyRequest companyRequest);

    /**
     * Deletes a company as an admin.
     * @param id the id of the company.
     * @return the company response.
     */
    CompanyResponse deleteCompanyAsAdmin(Long id);

    /**
     * Triggers the active status of a company.
     * @param id the id of the company.
     * @param isActive the new active status.
     * @return the company response.
     */
    CompanyResponse triggerActive(Long id, boolean isActive);
}
