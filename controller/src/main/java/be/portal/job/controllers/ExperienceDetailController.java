package be.portal.job.controllers;

import be.portal.job.dtos.experience_detail.responses.ExperienceDetailResponse;
import be.portal.job.services.IExperienceDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seekers/experience-details")
public class ExperienceDetailController {

    private final IExperienceDetailService experienceDetailService;

    @PreAuthorize("hasAnyAuthority('SEEKER')")
    @GetMapping
    public ResponseEntity<List<ExperienceDetailResponse>> getAllExperienceDetailsOfCurrentUser() {
        return ResponseEntity.ok(experienceDetailService.getAllByCurrentSeeker());
    }
}
