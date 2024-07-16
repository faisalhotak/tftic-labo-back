package be.portal.job.utils.datainitializer;

import be.portal.job.entities.SkillDetail;
import be.portal.job.repositories.SkillDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class SkillDetailInit implements CommandLineRunner {

    private final SkillDetailRepository skillDetailRepository;

    @Override
    public void run(String... args) throws Exception {

        List<SkillDetail> skillDetails = new ArrayList<>();

        skillDetails.add(new SkillDetail("Java Programming"));
        skillDetails.add(new SkillDetail("Project Management"));
        skillDetails.add(new SkillDetail("Data Analysis"));
        skillDetails.add(new SkillDetail("Web Development"));
        skillDetails.add(new SkillDetail("Machine Learning"));

        skillDetailRepository.saveAll(skillDetails);

    }
}
