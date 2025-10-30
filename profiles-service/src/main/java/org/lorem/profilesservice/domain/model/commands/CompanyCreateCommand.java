package org.lorem.profilesservice.domain.model.commands;

public record CompanyCreateCommand(
    String ruc,
    String nombreComercial
) {}

