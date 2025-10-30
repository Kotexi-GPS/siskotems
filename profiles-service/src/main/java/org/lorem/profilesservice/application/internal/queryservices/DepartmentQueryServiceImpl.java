package org.lorem.profilesservice.application.internal.queryservices;

import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.queries.DepartmentFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.DepartmentListByCompanyQuery;
import org.lorem.profilesservice.domain.services.DepartmentQueryService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentQueryServiceImpl implements DepartmentQueryService {

    private final DepartmentRepository departmentRepository;

    public DepartmentQueryServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> handle(DepartmentListByCompanyQuery query) {
        // repository doesn't expose a findByCompanyId, so filter in-memory.
        // If performance becomes an issue, add a query method in DepartmentRepository.
        return departmentRepository.findAll()
                .stream()
                .filter(d -> d.getCompany() != null && d.getCompany().getId() != null && d.getCompany().getId().equals(query.companyId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Department> handle(DepartmentFindByIdQuery query) {
        return departmentRepository.findById(query.departmentId());
    }
}