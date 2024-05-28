package be.portal.job.exceptions.skill_detail;

import be.portal.job.exceptions.NotFoundException;

public class SkillDetailNotFoundException extends NotFoundException {

    public SkillDetailNotFoundException() {
        super("SkillDetail not found");
    }
}
