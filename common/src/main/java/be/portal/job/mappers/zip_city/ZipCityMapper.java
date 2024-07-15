package be.portal.job.mappers.zip_city;

import be.portal.job.dtos.zip_city.requests.ZipCityRequest;
import be.portal.job.dtos.zip_city.responses.ZipCityResponse;
import be.portal.job.entities.ZipCity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ZipCityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ZipCity toEntity(ZipCityRequest request);

    ZipCityResponse fromEntity(ZipCity entity);
}
