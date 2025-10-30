package org.lorem.profilesservice.application.internal.commandservices;

import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.commands.DepartmentCreateCommand;
import org.lorem.profilesservice.domain.model.commands.DepartmentUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.DepartmentDeleteCommand;
import org.lorem.profilesservice.domain.services.DepartmentCommandService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.DepartmentRepository;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentCommandServiceImpl implements DepartmentCommandService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    public DepartmentCommandServiceImpl(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Department> handle(DepartmentCreateCommand command) {
        var optCompany = companyRepository.findById(command.companyId());
        if (optCompany.isEmpty()) {
            throw new IllegalArgumentException("Company not found with id: " + command.companyId());
        }

        var department = new Department();
        department.setName(command.name());
        department.setDescription(command.description());
        department.setCompany(optCompany.get());

        departmentRepository.save(department);
        return Optional.of(department);
    }

    @Override
    public void handle(DepartmentUpdateCommand command) {
        var opt = departmentRepository.findById(command.departmentId());
        if (opt.isEmpty()) return;

        var department = opt.get();
        department.setName(command.name());
        department.setDescription(command.description());

        departmentRepository.save(department);
    }

    @Override
    public void handle(DepartmentDeleteCommand command) {
        if (departmentRepository.findById(command.departmentId()).isPresent()) {
            departmentRepository.deleteById(command.departmentId());
        }
    }
}