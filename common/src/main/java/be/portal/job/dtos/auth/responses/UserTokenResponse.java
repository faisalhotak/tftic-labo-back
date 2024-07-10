package be.portal.job.dtos.auth.responses;

import be.portal.job.dtos.user.responses.UserShortResponse;
import be.portal.job.entities.Role;
import be.portal.job.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserTokenResponse(
        UserShortResponse user,
        Set<String> roles,
        String accessToken

) {
    public static UserTokenResponse fromEntityWithToken(User user, String token) {
        return new UserTokenResponse(
                new UserShortResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getPhoneNumber(),
                        user.getContactEmail()
                ),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                token
        );
    }
}
