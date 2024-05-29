package be.portal.job.exceptions.role;

import be.portal.job.exceptions.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
        public RoleNotFoundException() {
            super("Role not found");
        }
}
