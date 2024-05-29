package be.portal.job.controllers;

import be.portal.job.dtos.skill_set.requests.SkillSetRequest;
import be.portal.job.dtos.skill_set.requests.SkillSetUpdateRequest;
import be.portal.job.dtos.skill_set.responses.SkillSetResponse;
import be.portal.job.services.ISkillSetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skill-sets")
@PreAuthorize("hasAuthority('SEEKER')")
public class SkillSetController {

    private final ISkillSetService skillSetService;

    @GetMapping
    public ResponseEntity<List<SkillSetResponse>> getSkillSet() {
        return ResponseEntity.ok(skillSetService.getAllBySeeker());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillSetResponse> getSkillSetById(@PathVariable Long id) {
        return ResponseEntity.ok(skillSetService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SkillSetResponse> addSkillSet(@RequestBody @Valid SkillSetRequest request) {
        return ResponseEntity.ok(skillSetService.add(request));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillSetResponse> updateSkillSet(
            @PathVariable Long id,
            @RequestBody @Valid SkillSetUpdateRequest request
    ) {
        return ResponseEntity.ok(skillSetService.update(id, request));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillSetResponse> deleteSkillSet(@PathVariable Long id) {
        return ResponseEntity.ok(skillSetService.delete(id));
    }
}
