package be.portal.job.services.impls;

import be.portal.job.entities.ContractType;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.services.ContractTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractTypeImpl implements ContractTypeService {

    private final ContractTypeRepository contractTypeRepository;

    @Override
    public List<ContractType> getAllContractType() {
        return contractTypeRepository.findAll();
    }

    @Override
    public ContractType getContractTypeById(Long id) {
        return contractTypeRepository.findById(id).orElseThrow();
    }

    @Override
    public ContractType addContractType(ContractType contractType) {
        return contractTypeRepository.save(contractType);
    }

    @Override
    public ContractType updateContractType(Long id, ContractType contractType) {

        ContractType existingContractType = contractTypeRepository.findById(id).orElseThrow();
        existingContractType.setName(contractType.getName());
        existingContractType.setDescription(contractType.getDescription());

        return contractTypeRepository.save(existingContractType);
    }

    @Override
    public void deleteContractType(Long id) {
        contractTypeRepository.deleteById(id);
    }
}
