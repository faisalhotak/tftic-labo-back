package be.portal.job.services.impls;

import be.portal.job.dtos.skill_detail.requests.SkillDetailRequest;
import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import be.portal.job.entities.SkillDetail;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.skill_detail.SkillDetailNotFoundException;
import be.portal.job.repositories.SkillDetailRepository;
import be.portal.job.services.ISkillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillDetailServiceImpl implements ISkillDetailService {

    private final SkillDetailRepository skillDetailRepository;

    @Override
    public List<SkillDetailResponse> getAll() {
        return skillDetailRepository.findAll()
                .stream()
                .map(SkillDetailResponse::fromEntity)
                .toList();
    }

    @Override
    public SkillDetailResponse getSkillDetailById(Long id) {

        SkillDetail skillDetail = skillDetailRepository.findById(id).orElseThrow(SkillDetailNotFoundException::new);

        return SkillDetailResponse.fromEntity(skillDetail);
    }

    @Override
    public SkillDetailResponse addSkillDetail(SkillDetailRequest skillDetailRequest) {

        if (skillDetailRepository.findByName(skillDetailRequest.name()).isPresent()) {
            throw new NotAllowedException("Skill already exists");
        }

        SkillDetail skillDetail = skillDetailRequest.toEntity();

        return SkillDetailResponse.fromEntity(skillDetail);
    }

    @Override
    public SkillDetailResponse updateSkillDetail(Long id, SkillDetailRequest skillDetailRequest) {

        SkillDetail existingSkillDetail = skillDetailRepository.findById(id)
                .orElseThrow(SkillDetailNotFoundException::new);

        existingSkillDetail.setName(skillDetailRequest.name());

        skillDetailRepository.save(existingSkillDetail);

        return SkillDetailResponse.fromEntity(existingSkillDetail);
    }

    @Override
    public SkillDetailResponse deleteSkillDetail(Long id) {

        SkillDetail existingSkillDetail = skillDetailRepository.findById(id).orElseThrow(SkillDetailNotFoundException::new);

        skillDetailRepository.deleteById(id);

        return SkillDetailResponse.fromEntity(existingSkillDetail);
    }
}
