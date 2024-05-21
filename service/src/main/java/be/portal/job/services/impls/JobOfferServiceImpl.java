package be.portal.job.services.impls;

import be.portal.job.dtos.jobOffer.responses.JobOfferResponse;
import be.portal.job.repositories.JobOfferRepository;
import be.portal.job.services.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    @Override
    public List<JobOfferResponse> getAll() {
        return jobOfferRepository.findAll().stream()
                .map(JobOfferResponse::fromEntity)
                .toList();
    }
}
