package be.portal.job.services.impls;

import be.portal.job.dtos.experience_detail.requests.ExperienceDetailAddRequest;
import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;
import be.portal.job.entities.ExperienceDetail;
import be.portal.job.entities.JobSeeker;
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
    private final AuthServiceImpl authService;

    @Override
    public List<ExperienceDetailResponse> getAllByCurrentSeeker() {
        JobSeeker currentUser = (JobSeeker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return experienceDetailRepository.findAllByJobSeekerId(currentUser.getId()).stream()
                .map(ExperienceDetailResponse::fromEntity)
                .toList();
    }

    @Override
    public ExperienceDetailResponse addExperienceDetail(ExperienceDetailAddRequest experienceDetailRequest) {
        JobSeeker currentUser = authService.getAuthenticatedSeeker();
        return ExperienceDetailResponse.fromEntity(experienceDetailRepository.save(experienceDetailRequest.toEntity(currentUser)));
    }
}
