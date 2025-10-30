package org.lorem.profilesservice.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.profilesservice.domain.model.aggregates.Company;
import org.lorem.shared.domain.model.entities.AuditableModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "department")
public class Department extends AuditableModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public Department(String name, String description, Company company) {
        this();
        this.name = name;
        this.description = description;
        this.company = company;
    }

    public Department() {

    }

    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employee.setDepartment(null);
        this.employees.remove(employee);
    }
}