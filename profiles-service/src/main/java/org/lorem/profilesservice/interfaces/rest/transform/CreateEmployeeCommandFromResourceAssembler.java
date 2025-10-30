package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.EmployeeCreateCommand;
import org.lorem.profilesservice.interfaces.rest.resources.CreateEmployeeResource;

public class CreateEmployeeCommandFromResourceAssembler {
    public static EmployeeCreateCommand toCommandFromResource(CreateEmployeeResource resource) {
        return new EmployeeCreateCommand(
            resource.documentNumber(),
            resource.firstName(),
            resource.lastName(),
            resource.phone()
        );
    }
}

