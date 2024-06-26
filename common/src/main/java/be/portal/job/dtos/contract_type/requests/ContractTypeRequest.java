package be.portal.job.dtos.contract_type.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContractTypeRequest(

        @NotBlank(message = "You must provide a name for the contract type.")
        @Size(min = 1, max = 100)
        String name,

        @NotBlank(message = "You must provide a description for the contract type.")
        @Size(min = 10, max = 255)
        String description
) { }
