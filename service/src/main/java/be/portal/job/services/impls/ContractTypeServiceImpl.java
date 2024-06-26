package be.portal.job.services.impls;

import be.portal.job.dtos.contract_type.requests.ContractTypeRequest;
import be.portal.job.dtos.contract_type.responses.ContractTypeResponse;
import be.portal.job.entities.ContractType;
import be.portal.job.exceptions.NotAllowedException;
import be.portal.job.exceptions.contract_type.ContractTypeNotFoundException;
import be.portal.job.mappers.contract_type.ContractTypeMapper;
import be.portal.job.repositories.ContractTypeRepository;
import be.portal.job.services.IContractTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractTypeServiceImpl implements IContractTypeService {

    private final ContractTypeRepository contractTypeRepository;
    private final ContractTypeMapper contractTypeMapper;

    @Override
    public List<ContractTypeResponse> getAll() {
        return contractTypeRepository.findAll()
                .stream()
                .map(contractTypeMapper::fromEntity)
                .toList();
    }

    @Override
    public ContractTypeResponse getContractTypeById(Long id) {

        ContractType contractType = contractTypeRepository.findById(id).orElseThrow(ContractTypeNotFoundException::new);

        return contractTypeMapper.fromEntity(contractType);
    }

    @Override
    public ContractTypeResponse addContractType(ContractTypeRequest contractTypeRequest) {

        if (contractTypeRepository.findByName(contractTypeRequest.name()).isPresent()) {
            throw new NotAllowedException("Contract type already exists");
        }

        ContractType contractType = contractTypeMapper.toEntity(contractTypeRequest);

        return contractTypeMapper.fromEntity(contractTypeRepository.save(contractType));
    }

    @Override
    public ContractTypeResponse updateContractType(Long id, ContractTypeRequest contractTypeRequest) {

        ContractType existingContractType = contractTypeRepository.findById(id)
                .orElseThrow(ContractTypeNotFoundException::new);

        contractTypeMapper.updateEntityFromRequest(contractTypeRequest, existingContractType);

        return contractTypeMapper.fromEntity(contractTypeRepository.save(existingContractType));
    }

    @Override
    public ContractTypeResponse deleteContractType(Long id) {

        ContractType existingContractType = contractTypeRepository.findById(id)
                .orElseThrow(ContractTypeNotFoundException::new);

        contractTypeRepository.delete(existingContractType);

        return contractTypeMapper.fromEntity(existingContractType);
    }
}
