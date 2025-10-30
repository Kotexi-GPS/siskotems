package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.entities.Employee;
import org.lorem.profilesservice.domain.model.queries.EmployeeListByDepartmentQuery;
import org.lorem.profilesservice.domain.model.queries.EmployeeFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.EmployeeFindByDocumentQuery;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    List<Employee> handle(EmployeeListByDepartmentQuery query);
    Optional<Employee> handle(EmployeeFindByIdQuery query);
    Optional<Employee> handle(EmployeeFindByDocumentQuery query);
}