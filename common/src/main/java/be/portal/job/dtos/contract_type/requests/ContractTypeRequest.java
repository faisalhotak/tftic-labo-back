package be.portal.job.dtos.contract_type.requests;

import be.portal.job.entities.ContractType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContractTypeRequest(

        @NotBlank(message = "Contract type")
        @Size(min = 1, max = 100)
        String name,

        @NotBlank(message = "Description")
        @Size(min = 10, max = 255)
        String description
) {
    public ContractType toEntity() {

        ContractType contractType = new ContractType();

        contractType.setName(name);
        contractType.setDescription(description);

        return contractType;
    }
}
