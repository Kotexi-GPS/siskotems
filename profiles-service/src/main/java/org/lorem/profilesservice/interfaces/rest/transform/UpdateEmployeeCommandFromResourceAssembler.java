package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.EmployeeUpdateCommand;
import org.lorem.profilesservice.interfaces.rest.resources.UpdateEmployeeResource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateEmployeeCommandFromResourceAssembler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static EmployeeUpdateCommand toCommandFromResource(Long employeeId, UpdateEmployeeResource resource) {
        Date birthDate = parseDate(resource.birthDate());
        Date hireDate = parseDate(resource.hireDate());
        Date terminationDate = parseDate(resource.terminationDate());

        BigDecimal basicSalary = resource.basicSalary() != null ? BigDecimal.valueOf(resource.basicSalary()) : null;

        return new EmployeeUpdateCommand(
            employeeId,
            resource.departmentId(),
            resource.documentType(),
            resource.documentNumber(),
            resource.lastName(),
            resource.firstName(),
            birthDate,
            resource.gender(),
            resource.maritalStatus(),
            resource.address(),
            resource.district(),
            resource.province(),
            null, // departmentName (geographic)
            resource.email(),
            resource.phone(),
            hireDate,
            terminationDate,
            resource.contractType(),
            resource.workday(),
            resource.position(),
            resource.area(),
            resource.costCenter(),
            basicSalary,
            resource.familyAllowanceFlag(),
            resource.pensionRegime(),
            resource.afp(),
            resource.commissionType(),
            resource.cuspp(),
            resource.epsFlag(),
            resource.sctrFlag(),
            resource.lifeInsuranceFlag(),
            resource.bank(),
            resource.cci()
        );
    }

    private static Date parseDate(String dateStr) {
        if (dateStr == null) return null;
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}

