package be.portal.job.models.responses;

import be.portal.job.entities.User;

public record UserResponse(

        String firstname,
        String lastname,
        Integer phoneNumber
) {
        public static UserResponse fromEntity(User user) {
                return new UserResponse(
                        user.getFirstname(),
                        user.getLastname(),
                        user.getPhoneNumber()
                );
        }
}
