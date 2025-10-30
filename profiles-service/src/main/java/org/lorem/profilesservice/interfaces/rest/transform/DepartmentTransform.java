package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.interfaces.rest.resources.DepartmentResource;
import org.lorem.profilesservice.domain.model.entities.Department;

public final class DepartmentTransform {
    public static DepartmentResource toResource(Department d) {
        if (d==null) return null;
        Long companyId = d.getCompany() == null ? null : d.getCompany().getId();
        return new DepartmentResource(d.getId(), d.getName(), companyId);
    }
}

