package be.portal.job.services;

import be.portal.job.dtos.education_detail.requests.EducationDetailAddRequest;
import be.portal.job.dtos.education_detail.requests.EducationDetailUpdateRequest;
import be.portal.job.dtos.education_detail.responses.EducationDetailResponse;

import java.util.List;

public interface IEducationDetailService {

    /**
     * Retrieves a list of all education details associated with the seeker.
     * @return a list of {@code EducationDetailResponse} representing all education details for the seeker.
     */
    List<EducationDetailResponse> getAllBySeeker();

    /**
     * Retrieves the education detail by its unique identifier.
     * @param id the unique identifier of the education detail.
     * @return an {@code EducationDetailResponse} representing the education detail with the specified ID.
     */
    EducationDetailResponse getEducationDetailById(Long id);

    /**
     * Adds a new education detail based on the provided request.
     * @param requestAdd the request containing the details of the education to be added.
     * @return an {@code EducationDetailResponse} representing the added education detail.
     */
    EducationDetailResponse addEducationDetail(EducationDetailAddRequest requestAdd);

    /**
     * Updates an existing education detail based on the provided request.
     * @param requestUpdate the request containing the updated details of the education.
     * @return an {@code EducationDetailResponse} representing the updated education detail.
     */
    EducationDetailResponse updateEducationDetail(Long id, EducationDetailUpdateRequest requestUpdate);

    /**
     * Deletes the education detail by its unique identifier.
     * @param id the unique identifier of the education detail to be deleted.
     * @return an {@code EducationDetailResponse} representing the deleted education detail.
     */
    EducationDetailResponse deleteEducationDetail(Long id);
}
