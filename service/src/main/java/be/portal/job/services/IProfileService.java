package be.portal.job.services;

/**
 * Service interface for profile management in the system.
 */
public interface IProfileService {

        /**
        * Disables the profile of the current user.
        * @return a message indicating that the profile has been disabled.
        */
        String disableProfile();

        /**
         * Deletes the profile of the current user.
         * @return a message indicating that the profile has been deleted.
         */
        String deleteProfile();
}
