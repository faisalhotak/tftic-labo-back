package be.portal.job.controllers;

import be.portal.job.dtos.skill_detail.requests.SkillDetailRequest;
import be.portal.job.dtos.skill_detail.responses.SkillDetailResponse;
import be.portal.job.entities.SkillDetail;
import be.portal.job.services.SkillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skill-details")
public class SkillDetailController {

    private final SkillDetailService skillDetailService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SkillDetailResponse>> getAllSkillDetails(Model model) {
        List<SkillDetailResponse> skillDetails = skillDetailService.getAllSkillDetail().stream()
                .sorted(Comparator.comparing(SkillDetail::getId))
                .map(SkillDetailResponse::fromEntity)
                .toList();

        model.addAttribute("skillDetails", skillDetails);

        return ResponseEntity.ok().body(skillDetails);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillDetailResponse> getSkillDetailsById(@PathVariable Long id, Model model) {
        SkillDetailResponse skillDetailDTO = SkillDetailResponse.fromEntity(skillDetailService.getSkillDetailById(id));

        model.addAttribute("skillDetail", skillDetailDTO);

        return ResponseEntity.ok(skillDetailDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SkillDetailResponse> addSkillDetail(@RequestBody SkillDetailRequest form) {
        SkillDetail skillDetail = skillDetailService.addSkillDetail(form.toEntity());

        return ResponseEntity.ok(SkillDetailResponse.fromEntity(skillDetail));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<SkillDetailResponse> updateSkillDetails(@PathVariable Long id, @RequestBody SkillDetailRequest form) {
        SkillDetail skillDetail = skillDetailService.updateSkillDetail(id, form.toEntity());

        return ResponseEntity.ok(SkillDetailResponse.fromEntity(skillDetail));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SkillDetailResponse> deleteSocial(@PathVariable Long id) {
        skillDetailService.deleteSkillDetail(id);

        return ResponseEntity.ok().build();
    }
}
