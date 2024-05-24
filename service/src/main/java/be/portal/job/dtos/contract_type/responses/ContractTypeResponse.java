package be.portal.job.dtos.contract_type.responses;

import be.portal.job.entities.ContractType;

public record ContractTypeResponse(
        Long id,
        String name,
        String description
) {
    public static ContractTypeResponse fromEntity(ContractType contractType) {
        return new ContractTypeResponse(
                contractType.getId(),
                contractType.getName(),
                contractType.getDescription()
        );
    }
}
