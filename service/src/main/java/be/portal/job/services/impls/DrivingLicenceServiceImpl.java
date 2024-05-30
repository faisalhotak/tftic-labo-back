package be.portal.job.services.impls;

import be.portal.job.dtos.driving_licence.requests.DrivingLicenceRequest;
import be.portal.job.dtos.driving_licence.responses.DrivingLicenceResponse;
import be.portal.job.entities.DrivingLicence;
import be.portal.job.entities.JobSeeker;
import be.portal.job.exceptions.driving_licence.DrivingLicenceNotFound;
import be.portal.job.mappers.driving_licence.DrivingLicenceMapper;
import be.portal.job.repositories.DrivingLicenceRepository;
import be.portal.job.services.IAuthService;
import be.portal.job.services.IDrivingLicenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrivingLicenceServiceImpl implements IDrivingLicenceService {

    private final DrivingLicenceRepository drivingLicenceRepository;
    private final DrivingLicenceMapper drivingLicenceMapper;
    private final IAuthService authService;

    @Override
    public List<DrivingLicenceResponse> getAllBySeeker() {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return drivingLicenceRepository.findByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(drivingLicenceMapper::fromEntity)
                .toList();
    }

    @Override
    public DrivingLicenceResponse getById(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        DrivingLicence drivingLicence = drivingLicenceRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(DrivingLicenceNotFound::new);

        return drivingLicenceMapper.fromEntity(drivingLicenceRepository.save(drivingLicence));
    }

    @Override
    public DrivingLicenceResponse add(DrivingLicenceRequest requestAdd) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        DrivingLicence drivingLicence = drivingLicenceMapper.toEntity(requestAdd, jobSeeker);

        return drivingLicenceMapper.fromEntity(drivingLicenceRepository.save(drivingLicence));
    }

    @Override
    public DrivingLicenceResponse update(Long id, DrivingLicenceRequest requestUpdate) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        DrivingLicence drivingLicence = drivingLicenceRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(DrivingLicenceNotFound::new);

        drivingLicenceMapper.updateEntityFromRequest(requestUpdate, drivingLicence);

        return drivingLicenceMapper.fromEntity(drivingLicenceRepository.save(drivingLicence));
    }

    @Override
    public DrivingLicenceResponse delete(Long id) {
        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        DrivingLicence drivingLicence = drivingLicenceRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(DrivingLicenceNotFound::new);

        drivingLicenceRepository.delete(drivingLicence);

        return drivingLicenceMapper.fromEntity(drivingLicence);
    }
}
