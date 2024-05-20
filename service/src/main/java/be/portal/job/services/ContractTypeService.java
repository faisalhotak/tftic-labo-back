package be.portal.job.services;

import be.portal.job.entities.ContractType;

import java.util.List;

public interface ContractTypeService {

    /**
     * Retrieves all contract types from the system.
     * @return A list of ContractType objects representing all contract types.
     */
    List<ContractType> getAllContractType();

    /**
     * Retrieves a specific contract type by its ID.
     * @param id The ID of the contract type to retrieve.
     * @return The ContractType object representing the specified contract type, or null if not found.
     */
    ContractType getContractTypeById(Long id);

    /**
     * Adds a new contract type to the system.
     * @param contractType The ContractType object representing the contract type to be added.
     * @return The ContractType object representing the newly added contract type.
     */
    ContractType addContractType(ContractType contractType);

    /**
     * Updates an existing contract type in the system.
     * @param id The ID of the contract type to be updated.
     * @param contractType The ContractType object containing the updated details of the contract type.
     * @return The ContractType object representing the updated contract type, or null if not found.
     */
    ContractType updateContractType(Long id, ContractType contractType);

    /**
     * Deletes a contract type from the system.
     * @param id The ID of the contract type to be deleted.
     */
    void deleteContractType(Long id);
}
