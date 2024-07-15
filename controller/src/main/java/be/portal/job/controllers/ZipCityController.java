package be.portal.job.controllers;

import be.portal.job.dtos.zip_city.responses.ZipCityResponse;
import be.portal.job.services.IZipCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/zip-city")
public class ZipCityController {

    private final IZipCityService zipCityService;

    @GetMapping
    public ResponseEntity<List<ZipCityResponse>> getAllZipCities() {
        return ResponseEntity.ok(zipCityService.getAll());
    }
}
