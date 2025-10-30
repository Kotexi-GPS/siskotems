package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.CompanyCreateCommand;
import org.lorem.profilesservice.interfaces.rest.resources.CreateCompanyResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateCompanyCommandFromResourceAssembler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static CompanyCreateCommand toCommandFromResource(CreateCompanyResource resource) {
        Date fechaConstitucion = null;
        if (resource.fechaConstitucion() != null) {
            try {
                fechaConstitucion = dateFormat.parse(resource.fechaConstitucion());
            } catch (ParseException e) {
                // handle or ignore
            }
        }

        return new CompanyCreateCommand(
            resource.ruc(),
            resource.razonSocial(),
            resource.nombreComercial(),
            resource.regimenTributario(),
            resource.tipoEmpresa(),
            resource.remype(),
            resource.sectorEconomico(),
            fechaConstitucion,
            resource.direccion(),
            resource.representanteLegal(),
            resource.telefono(),
            resource.email()
        );
    }
}

