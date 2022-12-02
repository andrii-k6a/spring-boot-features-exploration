package org.kook.spring.boot.exploration.persistence;

import org.kook.spring.boot.exploration.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
