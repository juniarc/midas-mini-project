package dev.codejar.service;


import dev.codejar.controller.EmployeeController;
import dev.codejar.model.dto.EmployeeDto;
import dev.codejar.model.dto.HrDashboardDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.projection.HrDashboardProjection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;



    public List<EmployeeEntity> listEmployee(){
        return employeeRepository.findAll();
    }



    //ADD New Employee
    public EmployeeEntity createEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);

    }


    //UPDATE New Employee
    public ResponseEntity<EmployeeEntity> editEmployee(Integer id, EmployeeDto employeeDto){
        return employeeRepository.findById(id)
                .map(employeeEntity -> {
                    employeeEntity.setEmployeeName(employeeDto.getEmployeeName());
                    employeeEntity.setEmployeeEmail(employeeDto.getEmployeeEmail());
                    employeeEntity.setEmployeePhone(employeeDto.getEmployeePhone());
                    employeeEntity.setManagerId(employeeDto.getManagerId());


                    modelMapper.map(employeeEntity, EmployeeDto.class);

                    return ResponseEntity.ok(employeeRepository.save(employeeEntity));
                }).orElse(ResponseEntity.notFound().build());
    }


    //DELETE exitst Employee
    public ResponseEntity<Void> deleteEmployee(Integer id){
        Optional<EmployeeEntity> optional = employeeRepository.findById(id);
        if (optional.isPresent()){
            employeeRepository.delete(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    public EmployeeEntity getEmployeeById(Integer id){
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

}