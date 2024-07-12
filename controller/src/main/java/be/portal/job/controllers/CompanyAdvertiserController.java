package be.portal.job.controllers;

import be.portal.job.dtos.company_advertiser.responses.CompanyAdvertiserResponse;
import be.portal.job.services.ICompanyAdvertiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/company-advertiser")
public class CompanyAdvertiserController {

    private final ICompanyAdvertiserService companyAdvertiserService;

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<List<CompanyAdvertiserResponse>> getCompanyAdvertisersByJobAdvertiserId(@PathVariable Long id) {
        return ResponseEntity.ok(companyAdvertiserService.getCompanyAdvertiserByJobAdvertiserId(id));
    }

}
