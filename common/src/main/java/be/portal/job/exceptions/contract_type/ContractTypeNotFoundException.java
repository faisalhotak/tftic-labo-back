package be.portal.job.exceptions.contract_type;

import be.portal.job.exceptions.NotFoundException;

public class ContractTypeNotFoundException extends NotFoundException {

    public ContractTypeNotFoundException() {
        super("Contract type not found");
    }
}
