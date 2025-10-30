package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.DepartmentUpdateCommand;
import org.lorem.profilesservice.interfaces.rest.resources.UpdateDepartmentResource;

public class UpdateDepartmentCommandFromResourceAssembler {
    public static DepartmentUpdateCommand toCommandFromResource(Long departmentId, UpdateDepartmentResource resource) {
        return new DepartmentUpdateCommand(
            departmentId,
            resource.name(),
            resource.description()
        );
    }
}

