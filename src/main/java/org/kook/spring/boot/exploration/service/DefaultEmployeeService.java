package org.kook.spring.boot.exploration.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kook.spring.boot.exploration.entity.Employee;
import org.kook.spring.boot.exploration.exception.EmployeeNotFoundException;
import org.kook.spring.boot.exploration.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee saveNewEmployee(Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @Override
    public Employee replaceEmployee(Employee newEmployee, Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

}
