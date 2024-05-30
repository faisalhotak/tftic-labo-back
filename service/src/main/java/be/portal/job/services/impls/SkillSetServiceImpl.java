package be.portal.job.services.impls;

import be.portal.job.dtos.skill_set.requests.SkillSetRequest;
import be.portal.job.dtos.skill_set.responses.SkillSetResponse;
import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.SkillDetail;
import be.portal.job.entities.SkillSet;
import be.portal.job.exceptions.skill_detail.SkillDetailNotFoundException;
import be.portal.job.exceptions.skill_set.SkillSetNotFoundException;
import be.portal.job.mappers.skill_set.SkillSetMapper;
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
    private final SkillSetMapper skillSetMapper;

    @Override
    public List<SkillSetResponse> getAllBySeeker() {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        return skillSetRepository.findAllByJobSeekerId(jobSeeker.getId())
                .stream()
                .map(skillSetMapper::fromEntity)
                .toList();
    }

    @Override
    public SkillSetResponse getById(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillSet skillSet = skillSetRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(SkillSetNotFoundException::new);

        return skillSetMapper.fromEntity(skillSet);
    }

    @Override
    public SkillSetResponse add(SkillSetRequest request) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillDetail skillDetail = skillDetailRepository.findById(request.skillDetailId())
                .orElseThrow(SkillDetailNotFoundException::new);

        SkillSet skillSet = skillSetMapper.toEntity(request, jobSeeker, skillDetail);

        return skillSetMapper.fromEntity(skillSetRepository.save(skillSet));
    }

    @Override
    public SkillSetResponse update(Long id, SkillSetRequest request) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillSet skillSet = skillSetRepository
                .findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(SkillSetNotFoundException::new);

        SkillDetail skillDetail = skillDetailRepository.findById(request.skillDetailId())
                .orElseThrow(SkillDetailNotFoundException::new);

        skillSetMapper.updateEntityFromRequest(request, skillDetail, skillSet);

        return skillSetMapper.fromEntity(skillSetRepository.save(skillSet));
    }

    @Override
    public SkillSetResponse delete(Long id) {

        JobSeeker jobSeeker = authService.getAuthenticatedSeeker();

        SkillSet skillSet = skillSetRepository.findByIdAndJobSeekerId(id, jobSeeker.getId())
                .orElseThrow(SkillSetNotFoundException::new);

        skillSetRepository.deleteById(id);

        return skillSetMapper.fromEntity(skillSet);
    }
}
