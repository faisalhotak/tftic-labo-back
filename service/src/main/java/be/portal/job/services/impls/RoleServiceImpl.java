package be.portal.job.services.impls;

import be.portal.job.dtos.role.requests.RoleRequest;
import be.portal.job.dtos.role.responses.RoleResponse;
import be.portal.job.entities.Role;
import be.portal.job.exceptions.AlreadyExistsException;
import be.portal.job.exceptions.role.RoleNotFoundException;
import be.portal.job.mappers.role.RoleMapper;
import be.portal.job.repositories.RoleRepository;
import be.portal.job.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::fromEntity)
                .toList();
    }

    @Override
    public RoleResponse getByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);

        return roleMapper.fromEntity(role);
    }

    @Override
    public RoleResponse getById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        return roleMapper.fromEntity(role);
    }

    @Override
    public RoleResponse add(RoleRequest request) {
        if (roleRepository.findByName(request.name()).isPresent()) {
            throw new AlreadyExistsException("Role already exists");
        }

        Role role = roleMapper.toEntity(request);

        return roleMapper.fromEntity(roleRepository.save(role));
    }

    @Override
    public RoleResponse update(Long id, RoleRequest request) {
        Role existingRole = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        roleMapper.updateEntityFromRequest(request, existingRole);

        return roleMapper.fromEntity(roleRepository.save(existingRole));
    }

    @Override
    public RoleResponse delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        roleRepository.delete(role);

        return roleMapper.fromEntity(role);
    }
}
