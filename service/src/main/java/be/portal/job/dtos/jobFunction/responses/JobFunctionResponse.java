package be.portal.job.dtos.jobFunction.responses;

public record JobFunctionResponse(
        Long id,
        String name,
        String title
) {
    public static JobFunctionResponse fromEntity(be.portal.job.entities.JobFunction jobFunction) {
        return new JobFunctionResponse(
                jobFunction.getId(),
                jobFunction.getName(),
                jobFunction.getTitle()
        );
    }
}

