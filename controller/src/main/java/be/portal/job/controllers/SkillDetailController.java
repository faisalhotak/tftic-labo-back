package be.portal.job.controllers;

import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import be.portal.job.services.ISkillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/v1/skill-details")
public class SkillDetailController {

    private final ISkillDetailService skillDetailService;

    @GetMapping
    public ResponseEntity<List<SkillDetailResponse>> getAllSkillDetails() {
        return ResponseEntity.ok(skillDetailService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillDetailResponse> getSkillDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(skillDetailService.getSkillDetailById(id));
    }
}
