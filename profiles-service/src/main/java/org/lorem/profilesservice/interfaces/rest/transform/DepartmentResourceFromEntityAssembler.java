package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.interfaces.rest.resources.DepartmentResource;

public class DepartmentResourceFromEntityAssembler {
    public static DepartmentResource toResourceFromEntity(Department department) {
        if (department == null) return null;

        Long companyId = department.getCompany() != null ? department.getCompany().getId() : null;

        return new DepartmentResource(
            department.getId(),
            department.getName(),
            department.getDescription(),
            companyId
        );
    }
}

