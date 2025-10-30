package org.lorem.profilesservice.application.internal.commandservices;

import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.profilesservice.domain.model.commands.CompanyCreateCommand;
import org.lorem.profilesservice.domain.model.commands.CompanyUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.CompanyDeleteCommand;
import org.lorem.profilesservice.domain.model.valueobjects.ContactInfo;
import org.lorem.profilesservice.domain.model.valueobjects.FiscalInfo;
import org.lorem.profilesservice.domain.model.valueobjects.Location;
import org.lorem.profilesservice.domain.services.CompanyCommandService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {

    private final CompanyRepository companyRepository;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(CompanyCreateCommand command) {
        // Verificar duplicado por RUC
        if (companyRepository.findByRuc(command.ruc()).isPresent()) {
            throw new IllegalArgumentException("Company with given RUC already exists");
        }

        // usar constructor que acepta CompanyCreateCommand
        Company company = new Company(command);
        Company saved = companyRepository.save(company);
        return Optional.of(saved);
    }

    @Override
    public void handle(CompanyUpdateCommand command) {
        var opt = companyRepository.findById(command.companyId());
        if (opt.isEmpty()) return;

        var company = opt.get();

        company.setRazonSocial(command.razonSocial());
        company.setNombreComercial(command.nombreComercial());
        if (command.regimenTributario() != null) {
            company.setRegimenTributario(org.lorem.profilesservice.domain.model.valueobjects.RegimenTributario.fromCode(command.regimenTributario()));
        }
        if (command.tipoEmpresa() != null) {
            company.setTipoEmpresa(org.lorem.profilesservice.domain.model.valueobjects.CompanyType.fromCode(command.tipoEmpresa()));
        }
        company.setRemype(command.remype());
        company.setSectorEconomico(command.sectorEconomico());
        company.setFechaConstitucion(command.fechaConstitucion());

        // ContactInfo embebido (email, telefono)
        company.setContactInfo(new ContactInfo(command.email(), command.telefono()));

        // Mover dirección/representante legal a FiscalInfo / Location si aplica
        if (command.representanteLegal() != null || command.direccion() != null) {
            FiscalInfo fiscalInfo = company.getFiscalInfo();
            if (fiscalInfo == null) {
                fiscalInfo = new FiscalInfo();
            }
            fiscalInfo.setRepresentanteLegal(command.representanteLegal());
            fiscalInfo.setCompany(company);
            company.setFiscalInfo(fiscalInfo);
        }

        if (command.direccion() != null) {
            Location loc = company.getLocation();
            if (loc == null) {
                loc = new Location();
            }
            loc.setDireccion(command.direccion());
            company.setLocation(loc);
        }

        companyRepository.save(company);
    }

    @Override
    public void handle(CompanyDeleteCommand command) {
        if (companyRepository.findById(command.companyId()).isPresent()) {
            companyRepository.deleteById(command.companyId());
        }
    }
}