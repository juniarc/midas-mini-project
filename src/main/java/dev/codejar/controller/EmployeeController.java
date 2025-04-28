package dev.codejar.controller;

import dev.codejar.model.dto.EmployeeDto;
import dev.codejar.model.dto.TimesheetDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.model.entity.UserEntity;
import dev.codejar.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String getEmployees(Model model){
        List<EmployeeEntity> employeeList = employeeRepository.findAll();

        model.addAttribute("employeeList", employeeList);
        System.out.println("Employees found: " + employeeList);

        return "employee/index";
    }

    @GetMapping("/create")
    public String createTimesheet(Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeeDto", employeeDto);

        return "employee/create";
    }


    @PostMapping("/create")
    public String createTimesheet(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult result){
        System.out.println("Employe>>>"+employeeDto);

        if (result.hasErrors()){
            return "employee/create";
        }

            EmployeeEntity employee = new EmployeeEntity();
            employee.setEmployeeName(employeeDto.getEmployeeName());
            employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
            employee.setDateJoin(employeeDto.getDateJoin());
            employee.setManagerId(employeeDto.getManagerId());
            employee.setEmployeePhone(employeeDto.getEmployeePhone());


           employeeRepository.save(employee);
            return "redirect:/employees";

    }

    @GetMapping("/edit")
    public String updateEmployee(Model model, @RequestParam Integer userId){
        EmployeeEntity employee = employeeRepository.findById(userId).orElse(null);
        if(employee == null){
            return "redirect:/employees";
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setDateJoin(employee.getDateJoin());
        employeeDto.setManagerId(employee.getManagerId());
        employeeDto.setEmployeeEmail(employee.getEmployeeEmail());
        employeeDto.setEmployeePhone(employee.getEmployeePhone());

        model.addAttribute("employee", employee);
        model.addAttribute("employeeDto", employeeDto);

        return "employee/edit";

    }
}
