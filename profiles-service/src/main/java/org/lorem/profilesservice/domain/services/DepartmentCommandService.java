package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.commands.DepartmentCreateCommand;
import org.lorem.profilesservice.domain.model.commands.DepartmentUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.DepartmentDeleteCommand;

import java.util.Optional;

public interface DepartmentCommandService {
    Optional<Department> handle(DepartmentCreateCommand command);
    void handle(DepartmentUpdateCommand command);
    void handle(DepartmentDeleteCommand command);
}