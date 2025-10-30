package org.lorem.profilesservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.profilesservice.domain.model.commands.CompanyCreateCommand;
import org.lorem.profilesservice.domain.model.commands.CompanyUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.CompanyDeleteCommand;
import org.lorem.profilesservice.domain.model.queries.CompanyFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.CompanyFindByRucQuery;
import org.lorem.profilesservice.domain.model.queries.CompanyListAllQuery;
import org.lorem.profilesservice.domain.services.CompanyCommandService;
import org.lorem.profilesservice.domain.services.CompanyQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Companies", description = "Company Endpoints")
public class CompanyController {

    private final CompanyCommandService companyCommandService;
    private final CompanyQueryService companyQueryService;

    public CompanyController(CompanyCommandService companyCommandService, CompanyQueryService companyQueryService) {
        this.companyCommandService = companyCommandService;
        this.companyQueryService = companyQueryService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyCreateCommand command) {
        var created = companyCommandService.handle(command);
        return created.map(c -> ResponseEntity.ok(c)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId) {
        var query = new CompanyFindByIdQuery(companyId);
        var company = companyQueryService.handle(query);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Company>> listAllCompanies() {
        var companies = companyQueryService.handle(new CompanyListAllQuery());
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<Company> getCompanyByRuc(@PathVariable String ruc) {
        var company = companyQueryService.handle(new CompanyFindByRucQuery(ruc));
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update: expects a CompanyUpdateCommand in the request body (including companyId).
     * You can also call this endpoint with the companyId in the path if you prefer to construct
     * the command from the request body + path in a different style.
     */
    @PutMapping
    public ResponseEntity<?> updateCompany(@RequestBody CompanyUpdateCommand command) {
        companyCommandService.handle(command);
        return ResponseEntity.ok("Company updated successfully");
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
        companyCommandService.handle(new CompanyDeleteCommand(companyId));
        return ResponseEntity.ok("Company deleted successfully");
    }
}