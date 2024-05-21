package be.portal.job.controllers;

import be.portal.job.models.dtos.RoleDTO;
import be.portal.job.models.forms.RoleForm;
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
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> roles = roleService.getRoles()
                .stream()
                .map(RoleDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(roles);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        RoleDTO roleDTO = RoleDTO.fromEntity(roleService.getRoleById(id));

        return ResponseEntity.ok(roleDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<RoleDTO> addRole(@Valid @RequestBody RoleForm roleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        RoleDTO newRole = RoleDTO.fromEntity(roleService.addRole(roleForm.toEntity()));

        return ResponseEntity.ok(newRole);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @Valid @RequestBody RoleForm roleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        RoleDTO updatedRole = RoleDTO.fromEntity(roleService.updateRole(id, roleForm.toEntity()));

        return ResponseEntity.ok(updatedRole);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleDTO> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);

        return ResponseEntity.noContent().build();
    }
}
