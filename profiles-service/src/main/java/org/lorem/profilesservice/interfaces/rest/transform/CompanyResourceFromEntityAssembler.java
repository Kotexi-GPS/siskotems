package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.profilesservice.interfaces.rest.resources.CompanyResource;

import java.text.SimpleDateFormat;

public class CompanyResourceFromEntityAssembler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static CompanyResource toResourceFromEntity(Company company) {
        if (company == null) return null;

        String regimenTributario = company.getRegimenTributario() != null ? company.getRegimenTributario().name() : null;
        String tipoEmpresa = company.getTipoEmpresa() != null ? company.getTipoEmpresa().name() : null;
        String email = company.getContactInfo() != null ? company.getContactInfo().getEmail() : null;
        String telefono = company.getContactInfo() != null ? company.getContactInfo().getTelefono() : null;
        String direccion = company.getLocation() != null ? company.getLocation().getDireccion() : null;
        String representanteLegal = company.getFiscalInfo() != null ? company.getFiscalInfo().getRepresentanteLegal() : null;

        return new CompanyResource(
            company.getId(),
            company.getRuc(),
            company.getRazonSocial(),
            company.getNombreComercial(),
            regimenTributario,
            tipoEmpresa,
            company.getRemype(),
            company.getSectorEconomico(),
            company.getFechaConstitucion(),
            email,
            telefono,
            direccion,
            representanteLegal
        );
    }
}

