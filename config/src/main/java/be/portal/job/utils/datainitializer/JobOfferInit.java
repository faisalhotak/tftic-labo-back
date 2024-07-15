package be.portal.job.utils.datainitializer;

import be.portal.job.entities.*;
import be.portal.job.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(11)
public class JobOfferInit implements CommandLineRunner {

    private final JobOfferRepository jobOfferRepository;
    private final CompanyAdvertiserRepository companyAdvertiserRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final JobFunctionRepository jobFunctionRepository;
    private final ZipCityRepository zipCityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<ZipCity> zipCities = zipCityRepository.findAll();
        List<CompanyAdvertiser> companyAdvertisers = companyAdvertiserRepository.findAll();
        List<ContractType> contractTypes = contractTypeRepository.findAll();
        List<JobFunction> jobFunctions = jobFunctionRepository.findAll();

        List<JobOffer> jobOffers = List.of(
                new JobOffer("Description de l'offre 1", 25000, 45000, 30,  true, zipCities.get(0), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 2", 35000, 60000, 30,   true, zipCities.get(1), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 3", 30000, 50000, 30,   true, zipCities.get(2), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 4", 40000, 70000, 30,   true, zipCities.get(3), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 5", 28000, 48000, 30,   true, zipCities.get(4), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 6", 32000, 55000, 30,   true, zipCities.get(5), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 7", 30000, 50000, 30,   true, zipCities.get(6), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 8", 35000, 60000, 30,   true, zipCities.get(7), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 9", 40000, 70000, 30,   true, zipCities.get(8), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 10", 28000, 48000, 30,   true, zipCities.get(9), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 11", 32000, 55000, 30,   true, zipCities.get(10), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 12", 30000, 50000, 30,   true, zipCities.get(1), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 13", 35000, 60000, 30,   true, zipCities.get(2), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 14", 40000, 70000, 30,   true, zipCities.get(3), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 15", 28000, 48000, 30,   true, zipCities.get(4), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 16", 32000, 55000, 30,   true, zipCities.get(5), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 17", 30000, 50000, 30,   true, zipCities.get(6), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 18", 35000, 60000, 30,   true, zipCities.get(7), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 19", 40000, 70000, 30,   true, zipCities.get(8), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 20", 28000, 48000, 30,   true, zipCities.get(9), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 21", 32000, 55000, 30,   true, zipCities.get(10), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 22", 30000, 50000, 30,   true, zipCities.get(22), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 23", 35000, 60000, 30,   true, zipCities.get(32), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 24", 40000, 70000, 30,   true, zipCities.get(43), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 25", 28000, 48000, 30,   true, zipCities.get(53), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 26", 32000, 55000, 30,   true, zipCities.get(12), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1)),
                new JobOffer("Description de l'offre 27", 30000, 50000, 30,   true, zipCities.get(65), companyAdvertisers.get(2), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 28", 35000, 60000, 30,   true, zipCities.get(33), companyAdvertisers.get(3), contractTypes.get(2), jobFunctions.get(2)),
                new JobOffer("Description de l'offre 29", 40000, 70000, 30,   true, zipCities.get(1), companyAdvertisers.get(0), contractTypes.get(0), jobFunctions.get(0)),
                new JobOffer("Description de l'offre 30", 28000, 48000, 30,   true, zipCities.get(0), companyAdvertisers.get(1), contractTypes.get(1), jobFunctions.get(1))
        );

        jobOfferRepository.saveAll(jobOffers);
    }
}
