package be.portal.job.services.impls;

import be.portal.job.dtos.experience_detail.requests.ExperienceDetailAddRequest;
import be.portal.job.dtos.experience_detail.requests.ExperienceDetailUpdateRequest;
import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;
import be.portal.job.entities.ExperienceDetail;
import be.portal.job.entities.JobSeeker;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.mappers.experience_detail.ExperienceDetailMapper;
import be.portal.job.repositories.ExperienceDetailRepository;
import be.portal.job.services.IExperienceDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceDetailServiceImp implements IExperienceDetailService {

    private final ExperienceDetailRepository experienceDetailRepository;
    private final ExperienceDetailMapper experienceDetailMapper;
    private final AuthServiceImpl authService;

    @Override
    public List<ExperienceDetailResponse> getAllByCurrentSeeker() {
        JobSeeker currentUser = authService.getAuthenticatedSeeker();

        return experienceDetailRepository.findAllByJobSeekerId(currentUser.getId()).stream()
                .map(experienceDetailMapper::fromEntity)
                .toList();
    }

    @Override
    public ExperienceDetailResponse addExperienceDetail(ExperienceDetailAddRequest experienceDetailRequest) {
        JobSeeker currentUser = authService.getAuthenticatedSeeker();
        ExperienceDetail experienceDetail = experienceDetailMapper.toEntity(experienceDetailRequest, currentUser);

        return ExperienceDetailResponse.fromEntity(experienceDetailRepository.save(experienceDetail));
    }

    @Override
    public ExperienceDetailResponse updateExperienceDetail(Long id, ExperienceDetailAddRequest experienceDetailRequest) {
        JobSeeker currentUser = authService.getAuthenticatedSeeker();
        ExperienceDetail experienceDetail = experienceDetailRepository.findByIdAndJobSeekerId(id, currentUser.getId())
                .orElseThrow(() -> new NotFoundException("Experience detail notfound"));

        experienceDetailMapper.updateEntityFromRequest(experienceDetailRequest, experienceDetail);

        return ExperienceDetailResponse.fromEntity(experienceDetailRepository.save(experienceDetail));
    }

    @Override
    public ExperienceDetailResponse deleteExperienceDetail(Long id) {
        JobSeeker currentUser = authService.getAuthenticatedSeeker();
        ExperienceDetail experienceDetail = experienceDetailRepository.findByIdAndJobSeekerId(id, currentUser.getId())
                .orElseThrow(() -> new NotFoundException("Experience detail not found"));

        experienceDetailRepository.delete(experienceDetail);
        return ExperienceDetailResponse.fromEntity(experienceDetail);
    }
}
