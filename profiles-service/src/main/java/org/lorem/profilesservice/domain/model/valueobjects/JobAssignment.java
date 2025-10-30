package org.lorem.profilesservice.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.profilesservice.domain.model.entities.Employee;

import java.util.Date;

@Entity
@Table(name = "job_assignment")
@Getter
@Setter
public class JobAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "workday")
    private String workday;

    @Column(name = "position")
    private String position;

    @Column(name = "area")
    private String area;

    @Column(name = "cost_center")
    private String costCenter;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "termination_date")
    private Date terminationDate;

    // marca la asignación actualmente activa (si no quieres historial sólo guarda la actual)
    @Column(name = "active_flag")
    private Boolean activeFlag;

    public JobAssignment() {}
}