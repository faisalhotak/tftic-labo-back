package be.portal.job.services.impls;

import be.portal.job.entities.JobOffer;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    @Override
    public JobOffer findById(Long id) {
        return jobOfferRepository.findById(id).orElseThrow();
    }
}
