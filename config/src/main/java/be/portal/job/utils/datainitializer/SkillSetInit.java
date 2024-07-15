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
@Order(14)
public class SkillSetInit implements CommandLineRunner {

    private final JobSeekerRepository jobSeekerRepository;
    private final SkillSetRepository skillSetRepository;
    private final SkillDetailRepository skillDetailRepository;

    @Override
    public void run(String... args) throws Exception {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
        List<SkillDetail> skillDetails = skillDetailRepository.findAll();

        List<SkillSet> skillSets = new ArrayList<>();

        skillSets.add(new SkillSet(SkillLevel.INTERMEDIATE, 5, jobSeekers.get(0), skillDetails.get(0)));
        skillSets.add(new SkillSet(SkillLevel.EXPERT, 10, jobSeekers.get(1), skillDetails.get(1)));

        skillSetRepository.saveAll(skillSets);
    }
}
