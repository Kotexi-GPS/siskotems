package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.interfaces.rest.resources.CompanyResource;
import org.lorem.profilesservice.domain.model.aggregates.Company;

public final class CompanyTransform {
    public static CompanyResource toResource(Company c) {
        if (c==null) return null;
        return new CompanyResource(c.getId(), c.getRuc(), c.getNombreComercial(), c.getRazonSocial());
    }
}

