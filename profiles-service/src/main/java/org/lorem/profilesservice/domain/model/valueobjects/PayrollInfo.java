package org.lorem.profilesservice.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.profilesservice.domain.model.entities.Employee;

import java.math.BigDecimal;

@Entity
@Table(name = "payroll_info")
@Getter
@Setter
public class PayrollInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "basic_salary")
    private BigDecimal basicSalary;

    @Column(name = "family_allowance_flag")
    private Boolean familyAllowanceFlag;

    @Column(name = "pension_regime")
    private String pensionRegime;

    @Column(name = "afp")
    private String afp;

    @Column(name = "commission_type")
    private String commissionType;

    @Column(name = "cuspp")
    private String cuspp;

    @Column(name = "eps_flag")
    private Boolean epsFlag;

    @Column(name = "sctr_flag")
    private Boolean sctrFlag;

    @Column(name = "life_insurance_flag")
    private Boolean lifeInsuranceFlag;

    @Column(name = "bank")
    private String bank;

    @Column(name = "cci")
    private String cci;

    public PayrollInfo() {}
}