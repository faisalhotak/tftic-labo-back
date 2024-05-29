package be.portal.job.controllers;

import be.portal.job.dtos.company.requests.CompanyRequest;
import be.portal.job.dtos.company.responses.CompanyResponse;
import be.portal.job.services.ICompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody @Valid CompanyRequest company) {
        return ResponseEntity.ok(companyService.addCompany(company));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestBody @Valid CompanyRequest company
    ) {
        return ResponseEntity.ok(companyService.updateCompany(id, company));
    }

    @PreAuthorize("hasAnyAuthority('ADVERTISER', 'ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
}
