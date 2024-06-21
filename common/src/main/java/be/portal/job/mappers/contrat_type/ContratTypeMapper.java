package be.portal.job.mappers.contrat_type;

import be.portal.job.dtos.contract_type.requests.ContractTypeRequest;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.entities.ContractType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContratTypeMapper {

    @Mapping(target = "id", ignore= true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ContractType toEntity(ContractTypeRequest contractTypeRequest);

    ContractTypeResponse fromEntity(ContractType contractType);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(ContractTypeRequest contractTypeRequest, @MappingTarget ContractType contractType);
}
