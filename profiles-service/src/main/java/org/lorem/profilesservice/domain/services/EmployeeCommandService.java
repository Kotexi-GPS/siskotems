package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.entities.Employee;
import org.lorem.profilesservice.domain.model.commands.EmployeeCreateCommand;
import org.lorem.profilesservice.domain.model.commands.EmployeeUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.EmployeeDeleteCommand;

import java.util.Optional;

public interface EmployeeCommandService {
    Optional<Employee> handle(EmployeeCreateCommand command);
    void handle(EmployeeUpdateCommand command);
    void handle(EmployeeDeleteCommand command);
}