package be.portal.job.services.impls;

import be.portal.job.dtos.education_detail.requests.EducationDetailAddRequest;
import be.portal.job.dtos.education_detail.requests.EducationDetailUpdateRequest;
import be.portal.job.dtos.education_detail.responses.EducationDetailResponse;
import be.portal.job.entities.EducationDetail;
import be.portal.job.entities.JobSeeker;
import be.portal.job.exceptions.education_detail.EducationDetailNotFoundException;
import be.portal.job.mappers.education_detail.EducationDetailMapper;
import be.portal.job.repositories.EducationDetailRepository;
import be.portal.job.services.IAuthService;
import be.portal.job.services.IEducationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationDetailServiceImpl implements IEducationDetailService {

    private final EducationDetailRepository educationDetailRepository;
    private final EducationDetailMapper educationDetailMapper;
    private final IAuthService authService;

    @Override
    public List<EducationDetailResponse> getAllBySeeker() {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return educationDetailRepository.findByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(educationDetailMapper::fromEntity)
                .toList();
    }

    @Override
    public EducationDetailResponse getEducationDetailById(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        EducationDetail educationDetail = educationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(EducationDetailNotFoundException::new);

        return educationDetailMapper.fromEntity(educationDetailRepository.save(educationDetail));
    }

    @Override
    public EducationDetailResponse addEducationDetail(EducationDetailAddRequest requestAdd) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        EducationDetail educationDetail = educationDetailMapper.toEntity(requestAdd, jobSeeker);

        return educationDetailMapper.fromEntity(educationDetailRepository.save(educationDetail));
    }

    @Override
    public EducationDetailResponse updateEducationDetail(Long id, EducationDetailUpdateRequest requestUpdate) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        EducationDetail educationDetail = educationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(EducationDetailNotFoundException::new);

        educationDetail.setInstituteName(requestUpdate.instituteName());
        educationDetail.setMajor(requestUpdate.major());
        educationDetail.setDegreeType(requestUpdate.degreeType());
        educationDetail.setMention(requestUpdate.mention());
        educationDetail.setStartDate(requestUpdate.startDate());
        educationDetail.setCompletionDate(requestUpdate.completionDate());

        return educationDetailMapper.fromEntity(educationDetailRepository.save(educationDetail));
    }

    @Override
    public EducationDetailResponse deleteEducationDetail(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        EducationDetail educationDetail = educationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(EducationDetailNotFoundException::new);

        educationDetailRepository.deleteById(id);

        return educationDetailMapper.fromEntity(educationDetail);
    }
}
