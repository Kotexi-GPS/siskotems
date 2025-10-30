package org.lorem.profilesservice.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.profilesservice.domain.model.valueobjects.ContactInfo;
import org.lorem.profilesservice.domain.model.valueobjects.JobAssignment;
import org.lorem.profilesservice.domain.model.valueobjects.Location;
import org.lorem.profilesservice.domain.model.valueobjects.PayrollInfo;
import org.lorem.shared.domain.model.entities.AuditableModel;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee extends AuditableModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private java.util.Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Location location;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PayrollInfo payrollInfo;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<JobAssignment> assignments = new java.util.ArrayList<>();

    // Factory para crear empleado básico
    public static Employee createBasic(String documentNumber, String firstName, String lastName, String phone) {
        Employee e = new Employee();
        e.setDocumentNumber(documentNumber);
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setContactInfo(new ContactInfo(null, phone));
        return e;
    }

    // Constructor directo con los campos mínimos
    public Employee(String documentNumber, String firstName, String lastName, String phone) {
        this();
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = new ContactInfo(null, phone);
    }

    public Employee() {
        this.assignments = new java.util.ArrayList<>();
    }
}
