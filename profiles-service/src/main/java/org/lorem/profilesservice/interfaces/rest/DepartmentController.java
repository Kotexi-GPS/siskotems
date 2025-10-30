package org.lorem.profilesservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.lorem.profilesservice.domain.model.entities.Department;
import org.lorem.profilesservice.domain.model.commands.DepartmentCreateCommand;
import org.lorem.profilesservice.domain.model.commands.DepartmentUpdateCommand;
import org.lorem.profilesservice.domain.model.commands.DepartmentDeleteCommand;
import org.lorem.profilesservice.domain.model.queries.DepartmentFindByIdQuery;
import org.lorem.profilesservice.domain.model.queries.DepartmentListByCompanyQuery;
import org.lorem.profilesservice.domain.services.DepartmentCommandService;
import org.lorem.profilesservice.domain.services.DepartmentQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/departments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Departments", description = "Department Endpoints")
public class DepartmentController {

    private final DepartmentCommandService departmentCommandService;
    private final DepartmentQueryService departmentQueryService;

    public DepartmentController(DepartmentCommandService departmentCommandService, DepartmentQueryService departmentQueryService) {
        this.departmentCommandService = departmentCommandService;
        this.departmentQueryService = departmentQueryService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentCreateCommand command) {
        var created = departmentCommandService.handle(command);
        return created.map(d -> ResponseEntity.ok(d)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId) {
        var department = departmentQueryService.handle(new DepartmentFindByIdQuery(departmentId));
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * List departments by companyId using query parameter:
     * GET /api/v1/departments?companyId=123
     */
    @GetMapping(params = "companyId")
    public ResponseEntity<List<Department>> listDepartmentsByCompany(@RequestParam Long companyId) {
        var list = departmentQueryService.handle(new DepartmentListByCompanyQuery(companyId));
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentUpdateCommand command) {
        departmentCommandService.handle(command);
        return ResponseEntity.ok("Department updated successfully");
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        departmentCommandService.handle(new DepartmentDeleteCommand(departmentId));
        return ResponseEntity.ok("Department deleted successfully");
    }
}