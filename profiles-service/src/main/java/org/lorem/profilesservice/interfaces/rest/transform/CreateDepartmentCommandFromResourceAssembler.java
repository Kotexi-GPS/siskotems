package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.DepartmentCreateCommand;
import org.lorem.profilesservice.interfaces.rest.resources.CreateDepartmentResource;

public class CreateDepartmentCommandFromResourceAssembler {
    public static DepartmentCreateCommand toCommandFromResource(CreateDepartmentResource resource) {
        return new DepartmentCreateCommand(
            resource.companyId(),
            resource.name(),
            resource.description()
        );
    }
}

