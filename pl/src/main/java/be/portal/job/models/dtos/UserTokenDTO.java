package be.portal.job.models.dtos;

import be.portal.job.entities.Role;
import be.portal.job.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserTokenDTO {

    private Long id;
    private Set<String> roles;
    private String token;

    public UserTokenDTO(Long id, Set<Role> roles) {
        this.id = id;
        this.roles = roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    public static UserTokenDTO fromEntity(User user) {
        return new UserTokenDTO(user.getId(), user.getRoles());
    }
}
