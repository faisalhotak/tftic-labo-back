package be.portal.job.controllers.admin;

import be.portal.job.dtos.company.requests.CompanyIdRequest;
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
@RequestMapping("/api/admin/v1/companies")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin("*")
public class AdminCompanyController {

    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PostMapping("/{userId:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> addCompany(
            @PathVariable Long userId,
            @RequestBody @Valid CompanyRequest company
    ) {
        return ResponseEntity.ok(companyService.addCompanyAsAdmin(userId, company));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestBody @Valid CompanyRequest company
    ) {
        return ResponseEntity.ok(companyService.updateCompanyAsAdmin(id, company));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CompanyResponse> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompanyAsAdmin(id));
    }

    @PatchMapping("/activate")
    public ResponseEntity<CompanyResponse> activateCompany(@RequestBody @Valid CompanyIdRequest request) {
        return ResponseEntity.ok(companyService.triggerActive(request, true));
    }

    @PatchMapping("/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(@RequestBody @Valid CompanyIdRequest request) {
        return ResponseEntity.ok(companyService.triggerActive(request, false));
    }
}
