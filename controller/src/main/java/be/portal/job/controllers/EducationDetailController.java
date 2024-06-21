package be.portal.job.controllers;

import be.portal.job.dtos.education_detail.requests.EducationDetailAddRequest;
import be.portal.job.dtos.education_detail.requests.EducationDetailUpdateRequest;
import be.portal.job.dtos.education_detail.responses.EducationDetailResponse;
import be.portal.job.services.IEducationDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/education-details")
@PreAuthorize("hasAnyAuthority('SEEKER', 'ADMIN')")
public class EducationDetailController {

    private final IEducationDetailService educationDetailService;

    @GetMapping
    public ResponseEntity<List<EducationDetailResponse>> getAllEducationDetail() {
        return ResponseEntity.ok(educationDetailService.getAllBySeeker());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<EducationDetailResponse> getEducationDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(educationDetailService.getEducationDetailById(id));
    }

    @PostMapping
    public ResponseEntity<EducationDetailResponse> addEducationDetail(
            @RequestBody
            @Valid EducationDetailAddRequest addRequest) {
        return ResponseEntity.ok(educationDetailService.addEducationDetail(addRequest));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<EducationDetailResponse> updateEducationDetail(
            @PathVariable Long id,
            @RequestBody @Valid EducationDetailUpdateRequest updateRequest
            ) {
        return ResponseEntity.ok(educationDetailService.updateEducationDetail(id, updateRequest));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<EducationDetailResponse> deleteEducationDetail(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(educationDetailService.deleteEducationDetail(id));
    }
}
