package be.portal.job.controllers;

import be.portal.job.dtos.contract_type.requests.ContractTypeRequest;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.entities.ContractType;
import be.portal.job.services.ContractTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contract-types")
public class ContractTypeController {

    private final ContractTypeService contractTypeService;

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContractTypeResponse>> getAllContractTypes(Model model) {
        List<ContractTypeResponse> contractTypes = contractTypeService.getAllContractType().stream()
                .sorted(Comparator.comparing(ContractType::getId))
                .map(ContractTypeResponse::fromEntity)
                .toList();

        model.addAttribute("contractTypes", contractTypes);

        return ResponseEntity.ok().body(contractTypes);
    }

    @PreAuthorize("hasAnyAuthority('SEEKER', 'ADVERTISER', 'ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ContractTypeResponse> getContractTypeById(@PathVariable Long id, Model model) {
        ContractTypeResponse contractTypeDTO =  ContractTypeResponse.fromEntity(contractTypeService.getContractTypeById(id));

        model.addAttribute("contractTypeDTO", contractTypeDTO);

        return ResponseEntity.ok().body(contractTypeDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<ContractTypeResponse> addContractType(@RequestBody ContractTypeRequest form) {
        ContractType contractType = contractTypeService.addContractType(form.toEntity());

        return ResponseEntity.ok(ContractTypeResponse.fromEntity(contractType));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ContractTypeResponse> updateContractType(@PathVariable Long id, @RequestBody ContractTypeRequest form) {
        ContractType contractType = contractTypeService.updateContractType(id, form.toEntity());

        return ResponseEntity.ok(ContractTypeResponse.fromEntity(contractType));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ContractTypeResponse> deleteContractType(@PathVariable Long id) {
        contractTypeService.deleteContractType(id);

        return ResponseEntity.ok().build();
    }
}
