package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.queries.DepartmentListByCompanyQuery;
import org.lorem.profilesservice.domain.model.queries.DepartmentFindByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DepartmentQueryService {
    List<Department> handle(DepartmentListByCompanyQuery query);
    Optional<Department> handle(DepartmentFindByIdQuery query);
}