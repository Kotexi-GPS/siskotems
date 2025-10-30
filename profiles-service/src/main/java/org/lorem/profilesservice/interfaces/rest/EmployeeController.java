package org.lorem.profilesservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lorem.profilesservice.domain.model.entities.Employee;
import org.lorem.profilesservice.domain.model.commands.EmployeeCreateCommand;
import org.lorem.profilesservice.domain.model.commands.EmployeeUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.EmployeeDeleteCommand;
import org.lorem.profilesservice.domain.model.queries.EmployeeFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.EmployeeFindByDocumentQuery;
import org.lorem.profilesservice.domain.model.queries.EmployeeListByDepartmentQuery;
import org.lorem.profilesservice.domain.services.EmployeeCommandService;
import org.lorem.profilesservice.domain.services.EmployeeQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employees", description = "Employee Endpoints")
public class EmployeeController {

    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeeController(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeCreateCommand command) {
        var created = employeeCommandService.handle(command);
        return created.map(e -> ResponseEntity.ok(e)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
        var employee = employeeQueryService.handle(new EmployeeFindByIdQuery(employeeId));
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = "departmentId")
    public ResponseEntity<List<Employee>> listEmployeesByDepartment(@RequestParam Long departmentId) {
        var list = employeeQueryService.handle(new EmployeeListByDepartmentQuery(departmentId));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<Employee> getEmployeeByDocument(@PathVariable String documentNumber) {
        var employee = employeeQueryService.handle(new EmployeeFindByDocumentQuery(documentNumber));
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeUpdateCommand command) {
        employeeCommandService.handle(command);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        employeeCommandService.handle(new EmployeeDeleteCommand(employeeId));
        return ResponseEntity.ok("Employee deleted successfully");
    }
}