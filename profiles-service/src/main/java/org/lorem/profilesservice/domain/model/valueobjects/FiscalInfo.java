package org.lorem.profilesservice.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.profilesservice.domain.model.aggregates.Company;

@Entity
@Table(name = "company_fiscal_info")
@Getter
@Setter
public class FiscalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK hacia Company, unique para OneToOne
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false, unique = true)
    private Company company;

    // Ejemplos de campos fiscales separados
    @Column(name = "representante_legal")
    private String representanteLegal;

    @Column(name = "codigo_actividad")
    private String codigoActividad;

    @Column(name = "regimen_especial")
    private String regimenEspecial;

    public FiscalInfo() {}
}