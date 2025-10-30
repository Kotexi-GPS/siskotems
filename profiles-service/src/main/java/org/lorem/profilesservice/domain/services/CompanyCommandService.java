package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.profilesservice.domain.model.commands.CompanyCreateCommand;
import org.lorem.profilesservice.domain.model.commands.CompanyUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.CompanyDeleteCommand;

import java.util.Optional;

public interface CompanyCommandService {
    Optional<Company> handle(CompanyCreateCommand command);
    void handle(CompanyUpdateCommand command);
    void handle(CompanyDeleteCommand command);
}
