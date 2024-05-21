package be.portal.job.utils.datainitializer;

import be.portal.job.entities.CompanyAdvertiser;
import be.portal.job.entities.ContractType;
import be.portal.job.entities.JobFunction;
import be.portal.job.entities.JobOffer;
import be.portal.job.repositories.CompanyAdvertiserRepository;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.repositories.JobFunctionRepository;
import be.portal.job.repositories.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(8)
public class JobOfferInitializer implements CommandLineRunner {

    private final JobOfferRepository jobOfferRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final JobFunctionRepository jobFunctionRepository;

    @Override
    public void run(String... args) throws Exception {
        List<CompanyAdvertiser> companyAdvertisers = companyAdvertiserRepository.findAll();
        List<ContractType> contractTypes = contractTypeRepository.findAll();
        List<JobFunction> jobFunctions = jobFunctionRepository.findAll();

        // Créer quelques offres d'emploi avec des valeurs arbitraires
        List<JobOffer> jobOffers = List.of(

                new JobOffer("Description de l'offre 1", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 2", 25000, 45000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 3", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2))

        );

        jobOfferRepository.saveAll(jobOffers);
    }
}
