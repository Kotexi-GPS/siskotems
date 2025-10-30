package org.lorem.profilesservice.application.internal.queryservices;

import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.profilesservice.domain.model.queries.CompanyFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.CompanyFindByRucQuery;
import org.lorem.profilesservice.domain.model.queries.CompanyListAllQuery;
import org.lorem.profilesservice.domain.services.CompanyQueryService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {

    private final CompanyRepository companyRepository;

    public CompanyQueryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> handle(CompanyListAllQuery query) {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> handle(CompanyFindByIdQuery query) {
        return companyRepository.findById(query.companyId());
    }

    @Override
    public Optional<Company> handle(CompanyFindByRucQuery query) {
        return companyRepository.findByRuc(query.ruc());
    }
}