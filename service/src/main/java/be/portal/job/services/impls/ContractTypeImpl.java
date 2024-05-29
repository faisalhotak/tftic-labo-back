package be.portal.job.services.impls;

import be.portal.job.dtos.contract_type.requests.ContractTypeRequest;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.entities.ContractType;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.contract_type.ContractTypeNotFoundException;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.services.IContractTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractTypeImpl implements IContractTypeService {

    private final ContractTypeRepository contractTypeRepository;

    @Override
    public List<ContractTypeResponse> getAll() {
        return contractTypeRepository.findAll()
                .stream()
                .map(ContractTypeResponse::fromEntity)
                .toList();
    }

    @Override
    public ContractTypeResponse getContractTypeById(Long id) {

        ContractType contractType = contractTypeRepository.findById(id).orElseThrow(ContractTypeNotFoundException::new);

        return ContractTypeResponse.fromEntity(contractType);
    }

    @Override
    public ContractTypeResponse addContractType(ContractTypeRequest contractTypeRequest) {

        if (contractTypeRepository.findByName(contractTypeRequest.name()).isPresent()) {
            throw new NotAllowedException("Contract type already exists");
        }

        ContractType contractType = contractTypeRequest.toEntity();

        return ContractTypeResponse.fromEntity(contractType);
    }

    @Override
    public ContractTypeResponse updateContractType(Long id, ContractTypeRequest contractTypeRequest) {

        ContractType existingContractType = contractTypeRepository.findById(id)
                .orElseThrow(ContractTypeNotFoundException::new);

        existingContractType.setName(contractTypeRequest.name());
        existingContractType.setDescription(contractTypeRequest.description());

        contractTypeRepository.save(existingContractType);

        return ContractTypeResponse.fromEntity(existingContractType);
    }

    @Override
    public ContractTypeResponse deleteContractType(Long id) {

        ContractType existingContractType = contractTypeRepository.findById(id)
                .orElseThrow(ContractTypeNotFoundException::new);

        contractTypeRepository.delete(existingContractType);

        return ContractTypeResponse.fromEntity(existingContractType);
    }
}
