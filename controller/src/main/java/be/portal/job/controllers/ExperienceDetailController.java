package be.portal.job.controllers;

import be.portal.job.dtos.experience_detail.requests.ExperienceDetailRequest;
import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;
import be.portal.job.services.IExperienceDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/experience-details")
@PreAuthorize("hasAuthority('SEEKER')")
public class ExperienceDetailController {

    private final IExperienceDetailService experienceDetailService;

    @GetMapping
    public ResponseEntity<List<ExperienceDetailResponse>> getAllExperienceDetailsOfCurrentUser() {
        return ResponseEntity.ok(experienceDetailService.getAllByCurrentSeeker());
    }

    @PostMapping
    public ResponseEntity<ExperienceDetailResponse> addExperienceDetail(@RequestBody @Valid ExperienceDetailRequest experienceDetailRequest) {
        return ResponseEntity.ok(experienceDetailService.addExperienceDetail(experienceDetailRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDetailResponse> updateExperienceDetail(@PathVariable Long id, @RequestBody @Valid ExperienceDetailRequest experienceDetailRequest) {
        return ResponseEntity.ok(experienceDetailService.updateExperienceDetail(id, experienceDetailRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExperienceDetailResponse> deleteExperienceDetail(@PathVariable Long id) {
        return ResponseEntity.ok(experienceDetailService.deleteExperienceDetail(id));
    }
}
