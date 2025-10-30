package org.lorem.profilesservice.interfaces.rest.resources;

public record CreateEmployeeResource(
    String documentNumber,
    String firstName,
    String lastName,
    String phone
) {}

