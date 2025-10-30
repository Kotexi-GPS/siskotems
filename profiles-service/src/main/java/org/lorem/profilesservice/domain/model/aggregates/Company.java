package org.lorem.profilesservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.profilesservice.domain.model.commands.CompanyCreateCommand;
import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.valueobjects.*;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company extends AuditableAbstractAggregateRoot<Company> {

    @Column(name = "ruc", nullable = false, length = 20, unique = true)
    private String ruc;

    // razonSocial puede ser nulo al crear la Company de forma básica
    @Column(name = "razon_social", nullable = true)
    private String razonSocial;

    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Column(name = "regimen_tributario")
    private RegimenTributario regimenTributario;

    @Column(name = "tipo_empresa")
    private CompanyType tipoEmpresa;

    @Column(name = "remype")
    private Boolean remype;

    @Column(name = "sector_economico")
    private String sectorEconomico;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_constitucion")
    private Date fechaConstitucion;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Location location;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private FiscalInfo fiscalInfo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "company_user_ids", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "user_id")
    private List<Long> userIds = new ArrayList<>();

    public Company(
            String ruc,
            String razonSocial,
            String nombreComercial,
            String regimenTributario,
            String tipoEmpresa,
            Boolean remype,
            String sectorEconomico,
            Date fechaConstitucion,
            String direccion,
            String representanteLegal,
            String telefono,
            String email
    ) {
        this();
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.regimenTributario = RegimenTributario.fromCode(regimenTributario);
        this.tipoEmpresa = CompanyType.fromCode(tipoEmpresa);
        this.remype = remype;
        this.sectorEconomico = sectorEconomico;
        this.fechaConstitucion = fechaConstitucion;
        this.contactInfo = new ContactInfo(email, telefono);
    }

    public Company() {
        this.userIds = new ArrayList<>();
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        department.setCompany(this);
        this.departments.add(department);
    }

    public void removeDepartment(Department department) {
        department.setCompany(null);
        this.departments.remove(department);
    }

    public void setFiscalInfo(FiscalInfo fiscalInfo) {
        if (fiscalInfo == null) {
            if (this.fiscalInfo != null) {
                this.fiscalInfo.setCompany(null);
            }
            this.fiscalInfo = null;
        } else {
            fiscalInfo.setCompany(this);
            this.fiscalInfo = fiscalInfo;
        }
    }

    public Company(CompanyCreateCommand cmd) {
        this();
        this.ruc = cmd.ruc();
        this.nombreComercial = cmd.nombreComercial();
    }
}
