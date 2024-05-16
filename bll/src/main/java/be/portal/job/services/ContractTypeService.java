package be.portal.job.services;

import be.portal.job.entities.ContractType;

import java.util.List;

public interface ContractTypeService {

    List<ContractType> getAllContractType();

    ContractType getContractTypeById(Long id);

    ContractType addContractType(ContractType contractType);

    ContractType updateContractType(Long id, ContractType contractType);

    void deleteContractType(Long id);
}
