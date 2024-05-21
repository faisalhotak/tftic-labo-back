package be.portal.job.dtos.auth.responses;

import be.portal.job.entities.Role;
import be.portal.job.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserTokenResponse(

        Set<String> roles,
        String token

) {
    public static UserTokenResponse fromEntityWithToken(User user, String token) {
        return new UserTokenResponse(
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                token
        );
    }
}
