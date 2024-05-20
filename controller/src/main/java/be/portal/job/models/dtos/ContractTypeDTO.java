package be.portal.job.models.dtos;

import be.portal.job.entities.ContractType;

public record ContractTypeDTO(

        Long id,
        String name,
        String description
) {
    public static ContractTypeDTO fromEntity(ContractType contractType) {
        return new ContractTypeDTO(
                contractType.getId(),
                contractType.getName(),
                contractType.getDescription()
        );
    }
}
