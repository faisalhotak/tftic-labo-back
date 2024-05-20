package be.portal.job.services.impls;

import be.portal.job.entities.SkillDetail;
import be.portal.job.repositories.SkillDetailRepository;
import be.portal.job.services.SkillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillDetailServiceImpl implements SkillDetailService {

    private final SkillDetailRepository skillDetailRepository;

    @Override
    public List<SkillDetail> getAllSkillDetail() {
        return skillDetailRepository.findAll();
    }

    @Override
    public SkillDetail getSkillDetailById(Long id) {
        return skillDetailRepository.findById(id).orElseThrow();
    }

    @Override
    public SkillDetail addSkillDetail(SkillDetail skillDetail) {
        return skillDetailRepository.save(skillDetail);
    }

    @Override
    public SkillDetail updateSkillDetail(Long id, SkillDetail skillDetail) {

        SkillDetail existingSkillDetail = skillDetailRepository.findById(id).orElseThrow();
        existingSkillDetail.setName(skillDetail.getName());

        skillDetailRepository.save(existingSkillDetail);

        return existingSkillDetail;
    }

    @Override
    public void deleteSkillDetail(Long id) {
        skillDetailRepository.deleteById(id);
    }
}
