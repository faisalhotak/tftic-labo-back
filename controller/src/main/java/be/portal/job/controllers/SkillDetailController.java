package be.portal.job.controllers;

import be.portal.job.dtos.skill_detail.requests.SkillDetailRequest;
import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import be.portal.job.services.ISkillDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skill-details")
public class SkillDetailController {

    private final ISkillDetailService skillDetailService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SkillDetailResponse>> getAllSkillDetails() {
        return ResponseEntity.ok(skillDetailService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillDetailResponse> getSkillDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(skillDetailService.getSkillDetailById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<SkillDetailResponse> addSkillDetail(@RequestBody @Valid SkillDetailRequest skillDetail) {
        return ResponseEntity.ok(skillDetailService.addSkillDetail(skillDetail));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public  ResponseEntity<SkillDetailResponse> updateSkillDetails(@PathVariable Long id, @RequestBody @Valid SkillDetailRequest skillDetail) {
        return ResponseEntity.ok(skillDetailService.updateSkillDetail(id, skillDetail));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillDetailResponse> deleteSocial(@PathVariable Long id) {
        return ResponseEntity.ok(skillDetailService.deleteSkillDetail(id));
    }
}
