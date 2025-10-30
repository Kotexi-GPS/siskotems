package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.interfaces.rest.resources.EmployeeResource;
import org.lorem.profilesservice.domain.model.entities.Employee;

public final class EmployeeTransform {
    public static EmployeeResource toResource(Employee e) {
        if (e==null) return null;
        String phone = e.getContactInfo() == null ? null : e.getContactInfo().getPhone();
        return new EmployeeResource(e.getId(), e.getDocumentNumber(), e.getFirstName(), e.getLastName(), phone);
    }
}

