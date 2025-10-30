package org.lorem.profilesservice.interfaces.rest.resources;

public record DepartmentResource(
    Long id,
    String name,
    String description,
    Long companyId
) {}

