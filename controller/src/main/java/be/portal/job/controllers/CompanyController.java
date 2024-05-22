package be.portal.job.controllers;

import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.services.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@PreAuthorize("hasAnyAuthority('SEEKER','ADVERTISER', 'ADMIN')")
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }
}
