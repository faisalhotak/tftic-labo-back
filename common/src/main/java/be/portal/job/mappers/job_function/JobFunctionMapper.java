package be.portal.job.mappers.job_function;

import be.portal.job.dtos.job_function.requests.JobFunctionRequest;
import be.portal.job.dtos.job_function.responses.JobFunctionResponse;
import be.portal.job.entities.JobFunction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobFunctionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    JobFunction toEntity(JobFunctionRequest request);

    JobFunctionResponse fromEntity(JobFunction entity);

    void updateEntityFromRequest(JobFunctionRequest request, @MappingTarget JobFunction jobFunction);
}
