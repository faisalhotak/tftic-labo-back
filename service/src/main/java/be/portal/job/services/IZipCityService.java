package be.portal.job.services;

import be.portal.job.dtos.zip_city.responses.ZipCityResponse;

import java.util.List;

public interface IZipCityService {

    /**
     * Get all zip cities
     * @return List of ZipCityResponse
     */
    List<ZipCityResponse> getAll();
}
