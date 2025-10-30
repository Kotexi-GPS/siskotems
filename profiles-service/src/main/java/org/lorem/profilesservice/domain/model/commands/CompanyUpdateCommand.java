package org.lorem.profilesservice.domain.model.commands;

public record CompanyUpdateCommand(
    Long companyId,
    String razonSocial,
    String nombreComercial,
    String regimenTributario,
    String tipoEmpresa,
    Boolean remype,
    String sectorEconomico,
    java.util.Date fechaConstitucion,
    String direccion,
    String representanteLegal,
    String telefono,
    String email
) {}

