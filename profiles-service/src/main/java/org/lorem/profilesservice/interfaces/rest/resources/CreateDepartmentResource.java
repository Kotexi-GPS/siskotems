package org.lorem.profilesservice.interfaces.rest.resources;

public record CreateDepartmentResource(
    Long companyId,
    String name,
    String description
) {}

