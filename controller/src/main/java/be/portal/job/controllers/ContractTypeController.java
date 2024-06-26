package be.portal.job.controllers;

import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.services.IContractTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/v1/contract-types")
public class ContractTypeController {

    private final IContractTypeService contractTypeService;

    @GetMapping
    public ResponseEntity<List<ContractTypeResponse>> getAllContractTypes() {
        return ResponseEntity.ok(contractTypeService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ContractTypeResponse> getContractTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(contractTypeService.getContractTypeById(id));
    }
}
