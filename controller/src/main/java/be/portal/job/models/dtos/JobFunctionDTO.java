package be.portal.job.models.dtos;

import be.portal.job.entities.JobFunction;

public record JobFunctionDTO(
        String name,
        String title
) {
    public static JobFunctionDTO fromEntity(JobFunction jobFunction) {
        return new JobFunctionDTO(
                jobFunction.getName(),
                jobFunction.getTitle()
        );
    }
}
