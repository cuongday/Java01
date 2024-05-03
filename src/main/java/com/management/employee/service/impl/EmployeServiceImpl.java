package com.management.employee.service.impl;

import com.management.employee.domain.entity.Employee;
import com.management.employee.exception.ResourceNotFoundException;
import com.management.employee.repository.EmployeeRepository;
import com.management.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void generateEmployees(int numberOfEmployees) {

        Random random = new Random();
        List<Employee> employees = new ArrayList<Employee>();
        long count = this.employeeRepository.count();
        ArrayList<String> employeeAddress = new ArrayList<>();
        employeeAddress.add("Hà Nội");
        employeeAddress.add("Hồ Chí Minh");
        employeeAddress.add("Đà Nẵng");
        employeeAddress.add("Hải Phòng");
        employeeAddress.add("Cần Thơ");
        employeeAddress.add("Quảng Ninh");
        employeeAddress.add("Hải Dương");
        employeeAddress.add("Hà Nam");
        employeeAddress.add("Hà Tĩnh");

        ArrayList<String> employeeDepartment = new ArrayList<>();
        employeeDepartment.add("Phòng kế toán");
        employeeDepartment.add("Phòng nhân sự");
        employeeDepartment.add("Phòng kỹ thuật");
        employeeDepartment.add("Phòng marketing");
        employeeDepartment.add("Phòng kinh doanh");
        employeeDepartment.add("Phòng sản xuất");
        employeeDepartment.add("Phòng nghiên cứu");
        employeeDepartment.add("Phòng hành chính");
        employeeDepartment.add("Phòng quản lý");

        ArrayList<String> employeeName = new ArrayList<>();
        employeeName.add("Nguyễn Văn A");
        employeeName.add("Trần Văn B");
        employeeName.add("Lê Văn C");
        employeeName.add("Cao Trung D");
        employeeName.add("Nguyễn Thế E");
        employeeName.add("Dương Văn F");
        employeeName.add("Nguyễn Duy G");
        employeeName.add("Lý Văn H");

        DecimalFormat df = new DecimalFormat("#.##");
        if(count == 0){
            for(int i = 0; i< numberOfEmployees;i++){
                Employee employee = Employee.builder()
                        .name(employeeName.get(random.nextInt(8))+ (i+1))
                        .email("employee" + (i+1) + "@gmail.com")
                        .salary(random.nextInt(1000000, 20000000))
                        .dateofbirth(Date.valueOf(LocalDate.of(random.nextInt(1960, 2001), random.nextInt(1, 13), random.nextInt(1, 29))))
                        .department(employeeDepartment.get(random.nextInt(9)))
                        .Education("Tốt nghiệp đại học Công nghiệp Hà Nội với GPA " + df.format(random.nextDouble(1,4)) + "chuyên ngành Kỹ thuật phần mềm")
                        .address(employeeAddress.get(random.nextInt(9)))
                        .hobbies("Đọc sách, chơi game, nghe nhạc, luyện code, chơi thể thao")
                        .build();
                employees.add(employee);
            }
            this.employeeRepository.saveAll(employees);
        }

    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        try{
            ArrayList<Employee> employees = this.employeeRepository.findAll();
            return employees;
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("No employees found");
        }
    }

    @Override
    public Page<Employee> fetchEmployees(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findBySalaryGreaterThanEqual(double salary, Pageable pageable) {
        try{
            Page<Employee> employees = this.employeeRepository.findBySalaryGreaterThanEqual(salary, pageable);
            return employees;
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No employees have salary greater than equal " + salary);
        }
    }

    @Override
    public Page<Employee> findBySalaryLessThan(double salary, Pageable pageable) {
        try{
            Page<Employee> employees = this.employeeRepository.findBySalaryLessThan(salary, pageable);
            return employees;
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No employees have salary less than " + salary);
        }
    }

    @Override
    public Page<Employee> findByNameContainingAndSalaryGreaterThanEqual(String name, double salary, Pageable pageable) {
        try{
            Page<Employee> employees = this.employeeRepository.findByNameContainingAndSalaryGreaterThanEqual(name, salary, pageable);
            return employees;
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No employees have salary greater than " + salary + " and name contains " + name);
        }
    }

    @Override
    public Page<Employee> findByNameContainingAndSalaryLessThan(String name, double salary, Pageable pageable) {
        try{
            Page<Employee> employees = this.employeeRepository.findByNameContainingAndSalaryLessThan(name, salary, pageable);
            return employees;
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No employees have salary less than " + salary + " and name contains " + name);
        }
    }

    @Override
    public Page<Employee> findByDepartmentAndSalaryGreaterThanEqual(String department, double salary, Pageable pageable) {
        try{
            Page<Employee> employees = this.employeeRepository.findByDepartmentAndSalaryGreaterThanEqual(department, salary, pageable);
            return employees;
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No employees have salary greater than " + salary + " and department is " + department);
        }
    }

    @Override
    public Page<Employee> findByDepartmentAndSalaryLessThan(String department, double salary, Pageable pageable) {
        try{
            Page<Employee> employees = this.employeeRepository.findByDepartmentAndSalaryLessThan(department, salary, pageable);
            return employees;
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("No employees have salary less than " + salary + " and department is " + department);
        }
    }


}
