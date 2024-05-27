package be.portal.job.controllers;

import be.portal.job.dtos.role.requests.RoleAddRequest;
import be.portal.job.dtos.role.responses.RoleResponse;
import be.portal.job.services.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@CrossOrigin("*")
public class RoleController {

    public final IRoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getRoles() {
        List<RoleResponse> roles = roleService.getRoles()
                .stream()
                .map(RoleResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(roles);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse roleDTO = RoleResponse.fromEntity(roleService.getRoleById(id));

        return ResponseEntity.ok(roleDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<RoleResponse> addRole(@Valid @RequestBody RoleAddRequest roleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        RoleResponse newRole = RoleResponse.fromEntity(roleService.addRole(roleForm.toEntity()));

        return ResponseEntity.ok(newRole);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @Valid @RequestBody RoleAddRequest roleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        RoleResponse updatedRole = RoleResponse.fromEntity(roleService.updateRole(id, roleForm.toEntity()));

        return ResponseEntity.ok(updatedRole);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);

        return ResponseEntity.noContent().build();
    }
}
