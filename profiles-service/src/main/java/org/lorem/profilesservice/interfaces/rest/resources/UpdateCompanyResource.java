package org.lorem.profilesservice.interfaces.rest.resources;

public record UpdateCompanyResource(
    String razonSocial,
    String nombreComercial,
    String regimenTributario,
    String tipoEmpresa,
    Boolean remype,
    String sectorEconomico,
    String fechaConstitucion,
    String email,
    String telefono,
    String direccion,
    String representanteLegal
) {}

