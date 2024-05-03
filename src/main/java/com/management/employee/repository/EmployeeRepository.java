package com.management.employee.repository;

import com.management.employee.domain.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    ArrayList<Employee> findAll();
    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findBySalaryGreaterThanEqual(double salary, Pageable pageable);
    Page<Employee> findBySalaryLessThan(double salary, Pageable pageable);

    Page<Employee> findByNameContainingAndSalaryGreaterThanEqual(String name, double salary, Pageable pageable);
    Page<Employee> findByNameContainingAndSalaryLessThan(String name, double salary, Pageable pageable);

    Page<Employee> findByDepartment(String department, Pageable pageable);

    Page<Employee> findByDepartmentAndSalaryGreaterThanEqual(String department ,double salary, Pageable pageable);
    Page<Employee> findByDepartmentAndSalaryLessThan(String department ,double salary, Pageable pageable);
}
