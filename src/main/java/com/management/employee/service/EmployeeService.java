package com.management.employee.service;

import com.management.employee.domain.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface EmployeeService {
    void generateEmployees(int numberOfEmployees);
    ArrayList<Employee> getAllEmployees();
    Page<Employee> fetchEmployees(Pageable pageable);

    Page<Employee> findBySalaryGreaterThanEqual(double salary, Pageable pageable);
    Page<Employee> findBySalaryLessThan(double salary, Pageable pageable);

    Page<Employee> findByNameContainingAndSalaryGreaterThanEqual(String name, double salary, Pageable pageable);
    Page<Employee> findByNameContainingAndSalaryLessThan(String name, double salary, Pageable pageable);

    Page<Employee> findByDepartmentAndSalaryGreaterThanEqual(String department, double salary, Pageable pageable);
    Page<Employee> findByDepartmentAndSalaryLessThan(String department, double salary, Pageable pageable);

}
