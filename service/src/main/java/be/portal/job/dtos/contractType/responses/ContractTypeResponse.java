package be.portal.job.dtos.contractType.responses;

public record ContractTypeResponse(
        Long id,
        String name,
        String description
) {
    public static ContractTypeResponse fromEntity(be.portal.job.entities.ContractType contractType) {
        return new ContractTypeResponse(
                contractType.getId(),
                contractType.getName(),
                contractType.getDescription()
        );
    }
}
