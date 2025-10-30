package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.entities.Employee;
import org.lorem.profilesservice.interfaces.rest.resources.EmployeeResource;

import java.text.SimpleDateFormat;

public class EmployeeResourceFromEntityAssembler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static EmployeeResource toResourceFromEntity(Employee employee) {
        if (employee == null) return null;

        String email = employee.getContactInfo() != null ? employee.getContactInfo().getEmail() : null;
        String phone = employee.getContactInfo() != null ? employee.getContactInfo().getTelefono() : null;
        String address = employee.getLocation() != null ? employee.getLocation().getDireccion() : null;
        String district = employee.getLocation() != null ? employee.getLocation().getDistrito() : null;
        String province = employee.getLocation() != null ? employee.getLocation().getProvincia() : null;

        String birthDate = employee.getBirthDate() != null ? dateFormat.format(employee.getBirthDate()) : null;
        String hireDate = employee.getHireDate() != null ? dateFormat.format(employee.getHireDate()) : null;
        String terminationDate = employee.getTerminationDate() != null ? dateFormat.format(employee.getTerminationDate()) : null;

        Double basicSalary = null;
        Boolean familyAllowanceFlag = null;
        String pensionRegime = null;
        String afp = null;
        String commissionType = null;
        String cuspp = null;
        Boolean epsFlag = null;
        Boolean sctrFlag = null;
        Boolean lifeInsuranceFlag = null;
        String bank = null;
        String cci = null;

        if (employee.getPayrollInfo() != null) {
            basicSalary = employee.getPayrollInfo().getBasicSalary() != null ? employee.getPayrollInfo().getBasicSalary().doubleValue() : null;
            familyAllowanceFlag = employee.getPayrollInfo().getFamilyAllowanceFlag();
            pensionRegime = employee.getPayrollInfo().getPensionRegime();
            afp = employee.getPayrollInfo().getAfp();
            commissionType = employee.getPayrollInfo().getCommissionType();
            cuspp = employee.getPayrollInfo().getCuspp();
            epsFlag = employee.getPayrollInfo().getEpsFlag();
            sctrFlag = employee.getPayrollInfo().getSctrFlag();
            lifeInsuranceFlag = employee.getPayrollInfo().getLifeInsuranceFlag();
            bank = employee.getPayrollInfo().getBank();
            cci = employee.getPayrollInfo().getCci();
        }

        Long departmentId = employee.getDepartment() != null ? employee.getDepartment().getId() : null;

        return new EmployeeResource(
            employee.getId(),
            employee.getDocumentType(),
            employee.getDocumentNumber(),
            employee.getLastName(),
            employee.getFirstName(),
            birthDate,
            employee.getGender(),
            employee.getMaritalStatus(),
            email,
            phone,
            address,
            district,
            province,
            hireDate,
            terminationDate,
            employee.getContractType(),
            employee.getWorkday(),
            employee.getPosition(),
            employee.getArea(),
            employee.getCostCenter(),
            basicSalary,
            familyAllowanceFlag,
            pensionRegime,
            afp,
            commissionType,
            cuspp,
            epsFlag,
            sctrFlag,
            lifeInsuranceFlag,
            bank,
            cci,
            departmentId
        );
    }
}

