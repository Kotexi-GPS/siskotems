package org.lorem.profilesservice.application.internal.commandservices;

import org.lorem.profilesservice.domain.model.entities.Employee;
import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.commands.EmployeeCreateCommand;
import org.lorem.profilesservice.domain.model.commands.EmployeeUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.EmployeeDeleteCommand;
import org.lorem.profilesservice.domain.model.valueobjects.ContactInfo;
import org.lorem.profilesservice.domain.model.valueobjects.Location;
import org.lorem.profilesservice.domain.model.valueobjects.PayrollInfo;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Employee> handle(EmployeeCreateCommand command) {
        if (employeeRepository.findByDocumentNumber(command.documentNumber()).isPresent()) {
            throw new IllegalArgumentException("Employee already exists with document number: " + command.documentNumber());
        }

        var employee = new Employee();
        employee.setDocumentNumber(command.documentNumber());
        employee.setFirstName(command.firstName());
        employee.setLastName(command.lastName());
        // set phone into embedded ContactInfo
        employee.setContactInfo(new ContactInfo(null, command.phone()));

        employeeRepository.save(employee);
        return Optional.of(employee);
    }

    @Override
    public void handle(EmployeeUpdateCommand command) {
        var opt = employeeRepository.findById(command.employeeId());
        if (opt.isEmpty()) return;

        var employee = opt.get();

        // Department relation
        if (command.departmentId() != null) {
            Optional<Department> optDept = departmentRepository.findById(command.departmentId());
            optDept.ifPresent(employee::setDepartment);
        }

        employee.setDocumentType(command.documentType());
        employee.setDocumentNumber(command.documentNumber());
        employee.setLastName(command.lastName());
        employee.setFirstName(command.firstName());
        employee.setBirthDate(command.birthDate());
        employee.setGender(command.gender());
        employee.setMaritalStatus(command.maritalStatus());

        // Contact info
        employee.setContactInfo(new ContactInfo(command.email(), command.phone()));

        // Location (address/district/province) -> Location entity linked to employee
        if (command.address() != null || command.district() != null || command.province() != null) {
            Location loc = employee.getLocation();
            if (loc == null) {
                loc = new Location();
            }
            loc.setAddress(command.address());
            loc.setDistrict(command.district());
            loc.setProvince(command.province());
            loc.setEmployee(employee);
            employee.setLocation(loc);
        }

        // Job / payroll fields: create or update PayrollInfo
        if (command.basicSalary() != null
                || command.familyAllowanceFlag() != null
                || command.pensionRegime() != null
                || command.afp() != null) {
            PayrollInfo payroll = employee.getPayrollInfo();
            if (payroll == null) {
                payroll = new PayrollInfo();
            }
            payroll.setBasicSalary(command.basicSalary());
            payroll.setFamilyAllowanceFlag(command.familyAllowanceFlag());
            payroll.setPensionRegime(command.pensionRegime());
            payroll.setAfp(command.afp());
            payroll.setCommissionType(command.commissionType());
            payroll.setCuspp(command.cuspp());
            payroll.setEpsFlag(command.epsFlag());
            payroll.setSctrFlag(command.sctrFlag());
            payroll.setLifeInsuranceFlag(command.lifeInsuranceFlag());
            payroll.setBank(command.bank());
            payroll.setCci(command.cci());
            payroll.setEmployee(employee);
            employee.setPayrollInfo(payroll);
        }

        // Employment / assignment-like fields
        employee.setHireDate(command.hireDate());
        employee.setTerminationDate(command.terminationDate());
        employee.setContractType(command.contractType());
        employee.setWorkday(command.workday());
        employee.setPosition(command.position());
        employee.setArea(command.area());
        employee.setCostCenter(command.costCenter());

        employeeRepository.save(employee);
    }

    @Override
    public void handle(EmployeeDeleteCommand command) {
        if (employeeRepository.findById(command.employeeId()).isPresent()) {
            employeeRepository.deleteById(command.employeeId());
        }
    }
}