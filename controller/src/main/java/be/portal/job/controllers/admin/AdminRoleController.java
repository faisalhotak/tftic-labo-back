package be.portal.job.controllers.admin;

import be.portal.job.dtos.role.requests.RoleRequest;
import be.portal.job.dtos.role.responses.RoleResponse;
import be.portal.job.services.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/roles")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin("*")
public class AdminRoleController {

    private final IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping(params = "name")
    public ResponseEntity<RoleResponse> getRoleByName(@RequestParam String name) {
        return ResponseEntity.ok(roleService.getByName(name));
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> addRole(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.add(request));
    }

    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.update(id, request));
    }

    @DeleteMapping("/{id:^[0-9]+$}")
    public ResponseEntity<RoleResponse> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.delete(id));
    }
}
