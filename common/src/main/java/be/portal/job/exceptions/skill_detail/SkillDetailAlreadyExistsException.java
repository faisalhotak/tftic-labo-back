package be.portal.job.exceptions.skill_detail;

import be.portal.job.exceptions.AlreadyExistsException;

public class SkillDetailAlreadyExistsException extends AlreadyExistsException {

        public SkillDetailAlreadyExistsException() {
            super("Skill detail with this name already exists.");
        }
}
