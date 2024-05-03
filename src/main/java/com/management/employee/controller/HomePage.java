package com.management.employee.controller;

import com.management.employee.domain.entity.Employee;
import com.management.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class HomePage {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public String salaryOverPage(Model model,
                       @RequestParam("name") Optional<String> nameOptional,
                       @RequestParam("page") Optional<String> pageOptional){
        int page = 1;
        try{
            if(pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());
            }else{

            }

        } catch (NumberFormatException e) {

        }

        String name = nameOptional.isPresent() ? nameOptional.get() : "";

        Pageable pageable = PageRequest.of(page-1, 20);
        Page<Employee> employees = this.employeeService.findBySalaryGreaterThanEqual(10000000, pageable);

        List<Employee> employeeList = employees.getContent();

        int maxPagesToShow = 10;
        int startPage = Math.max(1, page - maxPagesToShow / 2);
        int endPage = Math.min(employees.getTotalPages(), startPage + maxPagesToShow - 1);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("employees", employeeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employees.getTotalPages());
        return "employee/show";
    }

    @GetMapping("/salary/under")
    public String salaryUnderPage(Model model,
                       @RequestParam("page") Optional<String> pageOptional){
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

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("employees", employeeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employees.getTotalPages());
        return "salaryUnder";
    }
}
