package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.CompanyUpdateCommand;
import org.lorem.profilesservice.interfaces.rest.resources.UpdateCompanyResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateCompanyCommandFromResourceAssembler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static CompanyUpdateCommand toCommandFromResource(Long companyId, UpdateCompanyResource resource) {
        Date fechaConstitucion = null;
        if (resource.fechaConstitucion() != null) {
            try {
                fechaConstitucion = dateFormat.parse(resource.fechaConstitucion());
            } catch (ParseException e) {
                // handle or ignore
            }
        }

        return new CompanyUpdateCommand(
            companyId,
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

