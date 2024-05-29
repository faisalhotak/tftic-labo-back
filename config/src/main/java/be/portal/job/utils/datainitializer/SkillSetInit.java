package be.portal.job.utils.datainitializer;

import be.portal.job.entities.JobSeeker;
import be.portal.job.entities.SkillDetail;
import be.portal.job.entities.SkillSet;
import be.portal.job.enums.SkillLevel;
import be.portal.job.repositories.JobSeekerRepository;
import be.portal.job.repositories.SkillDetailRepository;
import be.portal.job.repositories.SkillSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(13)
public class SkillSetInit implements CommandLineRunner {

    private final JobSeekerRepository jobSeekerRepository;
    private final SkillSetRepository skillSetRepository;
    private final SkillDetailRepository skillDetailRepository;

    @Override
    public void run(String... args) throws Exception {
        List<SkillSet> skillSets = new ArrayList<>();

        JobSeeker seeker1 = jobSeekerRepository.findByEmail("seeker1@example.com")
                .orElseThrow(() -> new RuntimeException("Seeker1 not found"));

        JobSeeker seeker2 = jobSeekerRepository.findByEmail("seeker2@example.com")
                .orElseThrow(() -> new RuntimeException("Seeker2 not found"));

        SkillDetail skillDetail1 = skillDetailRepository.findByName("Java Programming")
                .orElseThrow(() -> new RuntimeException("Java Programming skill not found"));

        SkillDetail skillDetail2 = skillDetailRepository.findByName("Project Management")
                .orElseThrow(() -> new RuntimeException("Project Management skill not found"));

        skillSets.add(new SkillSet(SkillLevel.INTERMEDIATE, 5, seeker1, skillDetail1));
        skillSets.add(new SkillSet(SkillLevel.EXPERT, 10, seeker2, skillDetail2));

        skillSetRepository.saveAll(skillSets);
    }
}
