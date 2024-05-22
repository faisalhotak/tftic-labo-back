package be.portal.job.services;

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
}
