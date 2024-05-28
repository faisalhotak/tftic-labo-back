package be.portal.job.services;

import be.portal.job.dtos.contract_type.requests.ContractTypeRequest;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;

import java.util.List;

public interface IContractTypeService {

    /**
     * Retrieves a list of all contract types.
     * @return a list of contract type responses.
     */
    List<ContractTypeResponse> getAll();

    /**
     * Retrieves a contract type by its ID.
     * @param id the ID of the contract type.
     * @return the contract type response.
     */
    ContractTypeResponse getContractTypeById(Long id);

    /**
     * Adds a new contract type.
     * @param contractType the request containing the data for the new contract type.
     * @return the response of the added contract type.
     */
    ContractTypeResponse addContractType(ContractTypeRequest contractType);

    /**
     * Updates an existing contract type.
     * @param id the ID of the contract type to be updated.
     * @param contractType the request containing the new data for the contract type.
     * @return the response of the updated contract type.
     */
    ContractTypeResponse updateContractType(Long id, ContractTypeRequest contractType);

    /**
     * Deletes a contract type by its ID.
     * @param id the ID of the contract type to be deleted.
     * @return the response of the deleted contract type.
     */
    ContractTypeResponse deleteContractType(Long id);
}
