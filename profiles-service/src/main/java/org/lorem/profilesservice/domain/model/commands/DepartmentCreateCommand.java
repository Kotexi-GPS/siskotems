package org.lorem.profilesservice.domain.model.commands;

public record DepartmentCreateCommand(Long companyId, String name, String description) {}

