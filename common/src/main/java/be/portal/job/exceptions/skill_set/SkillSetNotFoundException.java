package be.portal.job.exceptions.skill_set;

import be.portal.job.exceptions.NotFoundException;

public class SkillSetNotFoundException extends NotFoundException {

    public SkillSetNotFoundException() {
        super("Skill set not found");
    }
}
