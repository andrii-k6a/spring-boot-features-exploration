package org.kook.spring.boot.exploration.service;

import org.kook.spring.boot.exploration.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long id);

    Employee saveNewEmployee(Employee newEmployee);

    Employee replaceEmployee(Employee newEmployee, Long id);

    void deleteEmployee(Long id);
}
