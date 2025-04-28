package dev.codejar.controller;

import dev.codejar.model.dto.EmployeeDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public String getEmployees(Model model){
        List<EmployeeEntity> employeeList = employeeRepository.findAll();

        model.addAttribute("employeeList", employeeList);
        System.out.println("Employees found: " + employeeList);

        return "employee/index";
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
