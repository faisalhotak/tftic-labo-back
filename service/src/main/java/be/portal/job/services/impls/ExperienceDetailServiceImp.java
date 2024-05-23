package be.portal.job.services.impls;

import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;
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

    @Override
    public List<ExperienceDetailResponse> getAllByCurrentSeeker() {
        JobSeeker currentUser = (JobSeeker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return experienceDetailRepository.findAllByJobSeekerId(currentUser.getId()).stream()
                .map(ExperienceDetailResponse::fromEntity)
                .toList();
    }
}
