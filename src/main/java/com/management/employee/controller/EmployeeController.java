package com.management.employee.controller;

import com.management.employee.domain.entity.Employee;
import com.management.employee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/generate/{numOfEmployees}")
    public ResponseEntity<?> generateEmployee(@PathVariable int numOfEmployees){
        try {
            this.employeeService.generateEmployees(numOfEmployees);
            return ResponseEntity.ok("Employees generated successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        ArrayList<Employee> employees = this.employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/high-salary")
    public ResponseEntity<?> getHighSalaryEmployee(@RequestParam("page") Optional<String> pageOptional){
        int page = 1;
        try{
            if(pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());
            }else{

            }

        } catch (NumberFormatException e) {

        }

        Pageable pageable = PageRequest.of(page-1, 20);
        Page<Employee> employees = this.employeeService.findBySalaryGreaterThanEqual(10000000, pageable);
        List<Employee> employeeList = employees.getContent();
        int maxPagesToShow = 10;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(employees.getTotalPages(), startPage + maxPagesToShow - 1);

        Map<String, Object> response = new HashMap<>();
        response.put("startPage", startPage);
        response.put("endPage", endPage);
        response.put("employees", employeeList);
        response.put("currentPage", page);
        response.put("totalPages", employees.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/low-salary")
    public ResponseEntity<?> getLowSalaryEmployee(@RequestParam("page") Optional<String> pageOptional){
        int page = 1;
        try{
            if(pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());
            }else{

            }

        } catch (NumberFormatException e) {

        }

        Pageable pageable = PageRequest.of(page-1, 20);
        Page<Employee> employees = this.employeeService.findBySalaryLessThan(10000000, pageable);
        List<Employee> employeeList = employees.getContent();

        int maxPagesToShow = 10;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(employees.getTotalPages(), startPage + maxPagesToShow - 1);

        Map<String, Object> response = new HashMap<>();
        response.put("startPage", startPage);
        response.put("endPage", endPage);
        response.put("employees", employeeList);
        response.put("currentPage", page);
        response.put("totalPages", employees.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployee(@RequestParam String name,
                                            @RequestParam String salary,
                                            @RequestParam("page") Optional<String> pageOptional){
        int page = 1;
        try{
            if(pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());
            }

        } catch (NumberFormatException e) {

        }

        Pageable pageable = PageRequest.of(page-1, 20);
        Page<Employee> employees;

        double salaryValue = (Double) Double.parseDouble(salary);

        if (salaryValue >= 10000000) {
            employees = this.employeeService.findByNameContainingAndSalaryGreaterThanEqual(name, 10000000, pageable);
        } else {
            employees = this.employeeService.findByNameContainingAndSalaryLessThan(name, 10000000, pageable);
        }
        List<Employee> employeeList = employees.getContent();

        int maxPagesToShow = 10;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(employees.getTotalPages(), startPage + maxPagesToShow - 1);

        Map<String, Object> response = new HashMap<>();
        response.put("startPage", startPage);
        response.put("endPage", endPage);
        response.put("employees", employeeList);
        response.put("currentPage", page);
        response.put("totalPages", employees.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<?> getEmployeeByDepartment(@RequestParam String department,
                                                     @RequestParam String salary,
                                                     @RequestParam("page") Optional<String> pageOptional){

        int page = 1;
        try{
            if(pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());
            }

        } catch (NumberFormatException e) {

        }

        Pageable pageable = PageRequest.of(page-1, 20);
        Page<Employee> employees;

        double salaryValue = (Double) Double.parseDouble(salary);

        if (salaryValue >= 10000000) {
            employees = this.employeeService.findByDepartmentAndSalaryGreaterThanEqual(department, 10000000, pageable);
        } else {
            employees = this.employeeService.findByDepartmentAndSalaryLessThan(department, 10000000, pageable);
        }
        List<Employee> employeeList = employees.getContent();

        int maxPagesToShow = 10;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(employees.getTotalPages(), startPage + maxPagesToShow - 1);

        Map<String, Object> response = new HashMap<>();
        response.put("startPage", startPage);
        response.put("endPage", endPage);
        response.put("employees", employeeList);
        response.put("currentPage", page);
        response.put("totalPages", employees.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

