package be.portal.job.services;

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
}
