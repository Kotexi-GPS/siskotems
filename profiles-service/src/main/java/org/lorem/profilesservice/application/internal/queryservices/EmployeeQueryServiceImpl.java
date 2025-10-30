package org.lorem.profilesservice.application.internal.queryservices;

import org.lorem.profilesservice.domain.model.entities.Employee;
import org.lorem.profilesservice.domain.model.queries.EmployeeFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.EmployeeFindByDocumentQuery;
import org.lorem.profilesservice.domain.model.queries.EmployeeListByDepartmentQuery;
import org.lorem.profilesservice.domain.services.EmployeeQueryService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> handle(EmployeeListByDepartmentQuery query) {
        // repository doesn't expose findByDepartmentId, so filter in-memory.
        // Consider adding a repository method for efficiency when needed.
        return employeeRepository.findAll()
                .stream()
                .filter(e -> e.getDepartment() != null && e.getDepartment().getId() != null && e.getDepartment().getId().equals(query.departmentId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> handle(EmployeeFindByIdQuery query) {
        return employeeRepository.findById(query.employeeId());
    }

    @Override
    public Optional<Employee> handle(EmployeeFindByDocumentQuery query) {
        return employeeRepository.findByDocumentNumber(query.documentNumber());
    }
}