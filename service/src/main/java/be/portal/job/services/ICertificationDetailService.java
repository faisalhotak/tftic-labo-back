package be.portal.job.services;

import be.portal.job.dtos.certification_detail.requests.CertificationDetailRequest;
import be.portal.job.dtos.certification_detail.responses.CertificationDetailResponse;

import java.util.List;

public interface ICertificationDetailService {

    /**
     * Retrieves a list of all certification details for a job seeker.
     * @return a list of certification detail responses.
     */
    List<CertificationDetailResponse> getAllBySeeker();

    /**
     * Retrieves a certification detail by its ID.
     * @param id the ID of the certification detail.
     * @return the certification detail response.
     */
    CertificationDetailResponse getById(Long id);

    /**
     * Adds a new certification detail.
     * @param request the request containing the data for the new certification detail.
     * @return the response of the added certification detail.
     */
    CertificationDetailResponse add(CertificationDetailRequest request);

    /**
     * Updates an existing certification detail.
     * @param id the ID of the certification detail to be updated.
     * @param request the request containing the new data for the certification detail.
     * @return the response of the updated certification detail.
     */
    CertificationDetailResponse update(Long id, CertificationDetailRequest request);

    /**
     * Deletes a certification detail by its ID.
     * @param id the ID of the certification detail to be deleted.
     * @return the response of the deleted certification detail.
     */
    CertificationDetailResponse delete(Long id);
}
