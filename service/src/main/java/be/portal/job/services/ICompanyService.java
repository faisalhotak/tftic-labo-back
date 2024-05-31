package be.portal.job.services;

import be.portal.job.dtos.company.requests.CompanyIdRequest;
import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;

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
     *
     * @param id the identifier of the company to search for.
     * @return the company corresponding to the provided identifier, or null if not found.
     */
    CompanyResponse getCompanyById(Long id);

    /**
     * Creates a new company in the system.
     *
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
     * @param request the company id request.
     * @param isActive the active status.
     * @return the company response.
     */
    CompanyResponse triggerActive(CompanyIdRequest request, boolean isActive);
}
