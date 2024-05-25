package be.portal.job.dtos.job_function.responses;

import be.portal.job.entities.JobFunction;

public record JobFunctionResponse(
        Long id,
        String name,
        String title
) {
    public static JobFunctionResponse fromEntity(JobFunction jobFunction) {
        return new JobFunctionResponse(
                jobFunction.getId(),
                jobFunction.getName(),
                jobFunction.getTitle()
        );
    }
}

