package be.portal.job.services.impls;

import be.portal.job.dtos.skill_set.requests.SkillSetRequest;
import be.portal.job.dtos.skill_set.responses.SkillSetResponse;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.SkillDetail;
import be.portal.job.entities.SkillSet;
import be.portal.job.exceptions.skill_detail.SkillDetailNotFoundException;
import be.portal.job.exceptions.skill_set.SkillSetNotFoundException;
import be.portal.job.repositories.SkillDetailRepository;
import be.portal.job.repositories.SkillSetRepository;
import be.portal.job.services.ISkillSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillSetServiceImpl implements ISkillSetService {

    private final SkillSetRepository skillSetRepository;
    private final SkillDetailRepository skillDetailRepository;
    private final AuthServiceImpl authService;

    @Override
    public List<SkillSetResponse> getAllBySeeker() {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return skillSetRepository.findAllByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(SkillSetResponse::fromEntity)
                .toList();
    }

    @Override
    public SkillSetResponse getById(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillSet skillSet = skillSetRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(SkillSetNotFoundException::new);

        return SkillSetResponse.fromEntity(skillSet);
    }

    @Override
    public SkillSetResponse add(SkillSetRequest request) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillDetail skillDetail = skillDetailRepository.findById(request.skillDetailId())
                .orElseThrow(SkillDetailNotFoundException::new);

        SkillSet skillSet = request.toEntity(jobSeeker, skillDetail);

        return SkillSetResponse.fromEntity(skillSetRepository.save(skillSet));
    }

    @Override
    public SkillSetResponse update(Long id, SkillSetRequest request) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillSet skillSet = skillSetRepository
                .findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(SkillSetNotFoundException::new);

        SkillDetail skillDetail = skillDetailRepository.findById(request.skillDetailId())
                .orElseThrow(SkillDetailNotFoundException::new);

        skillSet.setSkillLevel(request.skillLevel());
        skillSet.setYears(request.years());
        skillSet.setSkillDetail(skillDetail);

        return SkillSetResponse.fromEntity(skillSetRepository.save(skillSet));
    }

    @Override
    public SkillSetResponse delete(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillSet skillSet = skillSetRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(SkillSetNotFoundException::new);

        skillSetRepository.deleteById(id);

        return SkillSetResponse.fromEntity(skillSet);
    }
}
