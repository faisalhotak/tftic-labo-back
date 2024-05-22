package be.portal.job.services.impls;

import be.portal.job.entities.Application;
import be.portal.job.exceptions.NotFoundException;
import be.portal.job.repositories.ApplicationRepository;
import be.portal.job.services.IApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationImpl  implements IApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplication(Long id) {
        return applicationRepository.findById(id).orElseThrow(()-> new NotFoundException("Application not found"));
    }

    @Override
    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(Long id, Application application) {
        Application existApplication = applicationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Application not found: " + id));

        existApplication.setApplyDate(application.getApplyDate());
        existApplication.setApplicationStatus(application.getApplicationStatus());

        return applicationRepository.save(existApplication);
    }

    @Override
    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new NotFoundException("Application not found: " + id);
        }
        applicationRepository.deleteById(id);
    }
}
