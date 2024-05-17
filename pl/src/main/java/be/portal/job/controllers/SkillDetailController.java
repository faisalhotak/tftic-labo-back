package be.portal.job.controllers;

import be.portal.job.entities.SkillDetail;
import be.portal.job.models.dtos.SkillDetailDTO;
import be.portal.job.models.forms.SkillDetailForm;
import be.portal.job.services.SkillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/skill-detail")
public class SkillDetailController {

    private final SkillDetailService skillDetailService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SkillDetailDTO>> getAllSkillDetails(Model model) {
        List<SkillDetailDTO> skillDetails = skillDetailService.getAllSkillDetail().stream()
                .sorted(Comparator.comparing(SkillDetail::getId))
                .map(SkillDetailDTO::fromEntity)
                .toList();

        model.addAttribute("skillDetails", skillDetails);

        return ResponseEntity.ok().body(skillDetails);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<SkillDetailDTO> getSkillDetailsById(@PathVariable Long id, Model model) {
        SkillDetailDTO skillDetailDTO = SkillDetailDTO.fromEntity(skillDetailService.getSkillDetailById(id));

        model.addAttribute("skillDetail", skillDetailDTO);

        return ResponseEntity.ok(skillDetailDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SkillDetailDTO> addSkillDetail(@RequestBody SkillDetailForm form) {
        SkillDetail skillDetail = skillDetailService.addSkillDetail(form.toEntity());

        return ResponseEntity.ok(SkillDetailDTO.fromEntity(skillDetail));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<SkillDetailDTO> updateSkillDetails(@PathVariable Long id, @RequestBody SkillDetailForm form) {
        SkillDetail skillDetail = skillDetailService.updateSkillDetail(id, form.toEntity());

        return ResponseEntity.ok(SkillDetailDTO.fromEntity(skillDetail));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SkillDetailDTO> deleteSocial(@PathVariable Long id) {
        skillDetailService.deleteSkillDetail(id);

        return ResponseEntity.ok().build();
    }
}
