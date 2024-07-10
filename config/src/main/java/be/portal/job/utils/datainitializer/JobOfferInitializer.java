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

        // Create some job offers with arbitrary values
        List<JobOffer> jobOffers = List.of(
                new JobOffer("Description de l'offre 1", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "1000 Brussels", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 2", 25000, 45000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "2000 Antwerp", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 3", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "3000 Leuven", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 4", 40000, 70000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "4000 Liège", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 5", 28000, 48000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "4000 Ghent", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 6", 32000, 55000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "5000 Namur", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 7", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "6000 Charleroi", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 8", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "7000 Mons", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 9", 40000, 70000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "8000 Bruges", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 10", 28000, 48000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "9000 Ostend", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 11", 32000, 55000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "1000 Brussels", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 12", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "2000 Antwerp", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 13", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "3000 Leuven", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 14", 40000, 70000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "4000 Liège", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 15", 28000, 48000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "4000 Ghent", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 16", 32000, 55000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "5000 Namur", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 17", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "6000 Charleroi", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 18", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "7000 Mons", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 19", 40000, 70000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "8000 Bruges", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 20", 28000, 48000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "9000 Ostend", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 21", 32000, 55000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "1000 Brussels", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 22", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "2000 Antwerp", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 23", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "3000 Leuven", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 24", 40000, 70000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "4000 Liège", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 25", 28000, 48000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "4000 Ghent", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 26", 32000, 55000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "5000 Namur", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 27", 30000, 50000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "6000 Charleroi", companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 28", 35000, 60000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "7000 Mons", companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 29", 40000, 70000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "8000 Bruges", companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 30", 28000, 48000, LocalDateTime.now(), 30, LocalDateTime.now().plusDays(30), true, "9000 Ostend", companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1))
        );

        jobOfferRepository.saveAll(jobOffers);
    }
}
