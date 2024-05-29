package be.portal.job.services.impls;

import be.portal.job.dtos.certification_detail.requests.CertificationDetailRequest;
import be.portal.job.dtos.certification_detail.requests.CertificationDetailUpdateRequest;
import be.portal.job.dtos.certification_detail.responses.CertificationDetailResponse;
import be.portal.job.entities.CertificationDetail;
import be.portal.job.entities.JobSeeker;
import be.portal.job.exceptions.certification_detail.CertificationDetailNotFoundException;
import be.portal.job.repositories.CertificationDetailRepository;
import be.portal.job.services.ICertificationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationDetailServiceImpl implements ICertificationDetailService {

    private final CertificationDetailRepository certificationDetailRepository;
    private final AuthServiceImpl authService;


    @Override
    public List<CertificationDetailResponse> getAllBySeeker() {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return certificationDetailRepository.findByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(CertificationDetailResponse::fromEntity)
                .toList();
    }

    @Override
    public CertificationDetailResponse getById(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(CertificationDetailNotFoundException::new);

        return CertificationDetailResponse.fromEntity(certificationDetail);
    }

    @Override
    @Transactional
    public CertificationDetailResponse add(CertificationDetailRequest certificationDetailRequest) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRequest.toEntity(jobSeeker);

        return CertificationDetailResponse.fromEntity(certificationDetailRepository.save(certificationDetail));
    }

    @Override
    @Transactional
    public CertificationDetailResponse update(Long id, CertificationDetailUpdateRequest request) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(CertificationDetailNotFoundException::new);

        certificationDetail.setName(request.name());
        certificationDetail.setDescription(request.description());
        certificationDetail.setCompletionDate(request.completionDate());

        return CertificationDetailResponse.fromEntity(certificationDetailRepository.save(certificationDetail));
    }

    @Override
    @Transactional
    public CertificationDetailResponse delete(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(CertificationDetailNotFoundException::new);

        certificationDetailRepository.deleteById(id);

        return CertificationDetailResponse.fromEntity(certificationDetail);
    }
}
