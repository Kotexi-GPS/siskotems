package org.lorem.profilesservice.interfaces.rest.resources;

public record CompanyResource(
    Long id,
    String ruc,
    String razonSocial,
    String nombreComercial,
    String regimenTributario,
    String tipoEmpresa,
    Boolean remype,
    String sectorEconomico,
    java.util.Date fechaConstitucion,
    String email,
    String telefono,
    String direccion,
    String representanteLegal
) {}

