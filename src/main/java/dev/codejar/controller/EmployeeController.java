package dev.codejar.controller;

import dev.codejar.model.dto.EmployeeDto;
import dev.codejar.model.dto.TimesheetDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.model.entity.UserEntity;
import dev.codejar.payload.response.BaseResponse;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.projection.HrDashboardProjection;
import dev.codejar.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Handler;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/all")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeService.listEmployee();
    }

    @GetMapping("/view")
    public List<HrDashboardProjection> getViewEmployee(){
        return employeeService.employeeViewPage();
    }


    @PostMapping("/create")
    public ResponseEntity<BaseResponse<EmployeeEntity>> createEmployee(@RequestBody EmployeeEntity employee){

        EmployeeEntity entity = employeeService.createEmployee(employee);

        BaseResponse<EmployeeEntity> response = new BaseResponse<>();
        response.setMessage("Success insert employee");
        response.setStatus(true);
        response.setData(entity);

        return ResponseEntity.ok(response);

    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeEntity> editEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto){
        return employeeService.editEmployee(id, employeeDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletedEmployee(@PathVariable("id") Integer id){
        return employeeService.deleteEmployee(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorHandler(MethodArgumentNotValidException e){
        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldError = ((FieldError) objectError).getField();
                    String messageError = objectError.getDefaultMessage();

                    errors.put(fieldError, messageError);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



//
//    @GetMapping
//    public String getEmployees(Model model){
//        List<EmployeeEntity> employeeList = employeeRepository.findAll();
//
//        model.addAttribute("employeeList", employeeList);
//        System.out.println("Employees found: " + employeeList);
//
//        return "employee/index";
//    }
//
//    @GetMapping("/create")
//    public String createTimesheet(Model model){
//        EmployeeDto employeeDto = new EmployeeDto();
//        model.addAttribute("employeeDto", employeeDto);
//
//        return "employee/create";
//    }
//
//
//    @PostMapping("/create")
//    public String createTimesheet(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult result){
//        System.out.println("Employe>>>"+employeeDto);
//
//        if (result.hasErrors()){
//            return "employee/create";
//        }
//
//            EmployeeEntity employee = new EmployeeEntity();
//            employee.setEmployeeName(employeeDto.getEmployeeName());
//            employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
//            employee.setDateJoin(employeeDto.getDateJoin());
//            employee.setManagerId(employeeDto.getManagerId());
//            employee.setEmployeePhone(employeeDto.getEmployeePhone());
//
//
//           employeeRepository.save(employee);
//            return "redirect:/employees";
//
//    }
//
//    @GetMapping("/edit")
//    public String updateEmployee(Model model, @RequestParam Integer id){
//        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
//        if(employee == null){
//            return "redirect:/employees";
//        }
//
//        EmployeeDto employeeDto = new EmployeeDto();
//
//
//        employeeDto.setEmployeeName(employee.getEmployeeName());
//        employeeDto.setDateJoin(employee.getDateJoin());
//        employeeDto.setManagerId(employee.getManagerId());
//        employeeDto.setEmployeeEmail(employee.getEmployeeEmail());
//        employeeDto.setEmployeePhone(employee.getEmployeePhone());
//
//        model.addAttribute("employee", employee);
//        model.addAttribute("employeeDto", employeeDto);
//
//        return "employee/edit";
//    }
//
//
//
//    @PostMapping("/edit")
//    public String editEmployee(Model model, @RequestParam int id, @Valid @ModelAttribute EmployeeDto employeeDto, BindingResult result) {
//
//        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
//
//        if (employee == null){
//            return "redirect:/timesheet";
//        }
//
//        model.addAttribute("timesheet", employee);
//
//
//        // update entity
//        employee.setEmployeeName(employeeDto.getEmployeeName());
//        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
//        employee.setManagerId(employeeDto.getManagerId());
//        employee.setEmployeePhone(employeeDto.getEmployeePhone());
//
//
//        employeeRepository.save(employee);
//        return "redirect:/employees";
//
//    }
//
//
//    @GetMapping("delete")
//    public String deleteEmployee(@RequestParam int id){
//        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
//
//        if (employee != null){
//            employeeRepository.delete(employee);
//        }
//
//        return "redirect:/employees";
//    }
}
