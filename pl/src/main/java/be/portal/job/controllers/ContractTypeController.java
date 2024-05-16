package be.portal.job.controllers;

import be.portal.job.entities.ContractType;
import be.portal.job.models.dtos.ContractTypeDTO;
import be.portal.job.models.forms.ContractTypeForm;
import be.portal.job.services.ContractTypeService;
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
@RequestMapping("/api/v1/contract-type")
public class ContractTypeController {

    private final ContractTypeService contractTypeService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContractTypeDTO>> getAllContractTypes(Model model) {
        List<ContractTypeDTO> contractTypes = contractTypeService.getAllContractType().stream()
                .sorted(Comparator.comparing(ContractType::getId))
                .map(ContractTypeDTO::fromEntity)
                .toList();

        model.addAttribute("contractTypes", contractTypes);

        return ResponseEntity.ok().body(contractTypes);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ContractTypeDTO> getContractTypeById(@PathVariable Long id, Model model) {
        ContractTypeDTO contractTypeDTO =  ContractTypeDTO.fromEntity(contractTypeService.getContractTypeById(id));

        model.addAttribute("contractTypeDTO", contractTypeDTO);

        return ResponseEntity.ok().body(contractTypeDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<ContractTypeDTO> addContractType(@RequestBody ContractTypeForm form) {
        ContractType contractType = contractTypeService.addContractType(form.toEntity());

        return ResponseEntity.ok(ContractTypeDTO.fromEntity(contractType));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ContractTypeDTO> updateContractType(@PathVariable Long id, @RequestBody ContractTypeForm form) {
        ContractType contractType = contractTypeService.updateContractType(id, form.toEntity());

        return ResponseEntity.ok(ContractTypeDTO.fromEntity(contractType));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ContractTypeDTO> deleteContractType(@PathVariable Long id) {
        contractTypeService.deleteContractType(id);

        return ResponseEntity.ok().build();
    }
}
