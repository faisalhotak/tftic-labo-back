package be.portal.job.services.impls;

import be.portal.job.dtos.zip_city.responses.ZipCityResponse;
import be.portal.job.mappers.zip_city.ZipCityMapper;
import be.portal.job.repositories.ZipCityRepository;
import be.portal.job.services.IZipCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZipCityServiceImpl implements IZipCityService {

    private final ZipCityRepository zipCityRepository;
    private final ZipCityMapper zipCityMapper;

    @Override
    public List<ZipCityResponse> getAll() {
        return zipCityRepository.findAll().stream()
                .map(zipCityMapper::fromEntity)
                .toList();
    }
}
