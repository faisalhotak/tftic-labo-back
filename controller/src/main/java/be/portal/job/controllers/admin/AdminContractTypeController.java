package be.portal.job.controllers.admin;

import be.portal.job.dtos.contract_type.requests.ContractTypeRequest;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.services.IContractTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/contract-types")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminContractTypeController {

    private final IContractTypeService contractTypeService;

    @GetMapping
    public ResponseEntity<List<ContractTypeResponse>> getAllContractTypes() {
        return ResponseEntity.ok(contractTypeService.getAll());
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ContractTypeResponse> getContractTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(contractTypeService.getContractTypeById(id));
    }

    @PostMapping
    public ResponseEntity<ContractTypeResponse> addContractType(@RequestBody @Valid ContractTypeRequest contractType) {
        return ResponseEntity.ok(contractTypeService.addContractType(contractType));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ContractTypeResponse> updateContractType(
            @PathVariable Long id,
            @RequestBody @Valid ContractTypeRequest contractType
    ) {
        return ResponseEntity.ok(contractTypeService.updateContractType(id, contractType));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ContractTypeResponse> deleteContractType(@PathVariable Long id) {
        return ResponseEntity.ok(contractTypeService.deleteContractType(id));
    }
}
