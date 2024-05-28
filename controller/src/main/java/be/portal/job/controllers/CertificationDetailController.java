package be.portal.job.controllers;


import be.portal.job.dtos.certification_detail.requests.CertificationDetailRequest;
import be.portal.job.dtos.certification_detail.requests.CertificationDetailUpdateRequest;
import be.portal.job.dtos.certification_detail.responses.CertificationDetailResponse;
import be.portal.job.services.ICertificationDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certifications")
@PreAuthorize("hasAuthority('SEEKER')")
public class CertificationDetailController {

    private final ICertificationDetailService certificationDetailService;

    @GetMapping
    public ResponseEntity<List<CertificationDetailResponse>> getAll() {
        return ResponseEntity.ok(certificationDetailService.getAllBySeeker());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CertificationDetailResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(certificationDetailService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CertificationDetailResponse> add(@RequestBody @Valid CertificationDetailRequest request) {
        return ResponseEntity.ok(certificationDetailService.add(request));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CertificationDetailResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid CertificationDetailUpdateRequest request
    ) {
        return ResponseEntity.ok(certificationDetailService.update(id, request));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<CertificationDetailResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(certificationDetailService.delete(id));
    }
}
