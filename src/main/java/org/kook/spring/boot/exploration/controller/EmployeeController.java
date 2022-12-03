package org.kook.spring.boot.exploration.controller;

import lombok.RequiredArgsConstructor;
import org.kook.spring.boot.exploration.assembler.EmployeeModelAssembler;
import org.kook.spring.boot.exploration.entity.Employee;
import org.kook.spring.boot.exploration.service.EmployeeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeModelAssembler employeeModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Employee>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return employeeModelAssembler.toCollectionModel(employees);
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return employeeModelAssembler.toModel(employee);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Employee>> newEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(employeeService.saveNewEmployee(newEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.replaceEmployee(newEmployee, id);
    }

    @PatchMapping("/{id}")
    public Employee modifyEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.modifyEmployee(newEmployee, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}
