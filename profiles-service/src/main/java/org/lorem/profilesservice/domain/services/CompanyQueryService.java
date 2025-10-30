package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.profilesservice.domain.model.queries.CompanyListAllQuery;
import org.lorem.profilesservice.domain.model.queries.CompanyFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.CompanyFindByRucQuery;

import java.util.List;
import java.util.Optional;

public interface CompanyQueryService {
    List<Company> handle(CompanyListAllQuery query);
    Optional<Company> handle(CompanyFindByIdQuery query);
    Optional<Company> handle(CompanyFindByRucQuery query);
}