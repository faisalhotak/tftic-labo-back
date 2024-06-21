package be.portal.job.services.impls;

import be.portal.job.dtos.certification_detail.requests.CertificationDetailRequest;
import be.portal.job.dtos.certification_detail.responses.CertificationDetailResponse;
import be.portal.job.entities.CertificationDetail;
import be.portal.job.entities.JobSeeker;
import be.portal.job.exceptions.certification_detail.CertificationDetailNotFoundException;
import be.portal.job.mappers.certification_detail.CertificationDetailMapper;
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
    private final CertificationDetailMapper certificationDetailMapper;
    private final AuthServiceImpl authService;


    @Override
    public List<CertificationDetailResponse> getAllBySeeker() {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return certificationDetailRepository.findByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(certificationDetailMapper::fromEntity)
                .toList();
    }

    @Override
    public CertificationDetailResponse getById(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(CertificationDetailNotFoundException::new);

        return certificationDetailMapper.fromEntity(certificationDetail);
    }

    @Override
    @Transactional
    public CertificationDetailResponse add(CertificationDetailRequest certificationDetailRequest) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailMapper.toEntity(jobSeeker);

        return certificationDetailMapper.fromEntity(certificationDetailRepository.save(certificationDetail));
    }

    @Override
    @Transactional
    public CertificationDetailResponse update(Long id, CertificationDetailRequest request) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(CertificationDetailNotFoundException::new);

        certificationDetailMapper.updateEntityFromRequest(request, certificationDetail);

        return certificationDetailMapper.fromEntity(certificationDetailRepository.save(certificationDetail));
    }

    @Override
    @Transactional
    public CertificationDetailResponse delete(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        CertificationDetail certificationDetail = certificationDetailRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(CertificationDetailNotFoundException::new);

        certificationDetailRepository.deleteById(id);

        return certificationDetailMapper.fromEntity(certificationDetail);
    }
}
