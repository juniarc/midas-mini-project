package dev.codejar.controller;


import dev.codejar.model.dto.TimesheetDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.model.entity.UserEntity;
import dev.codejar.payload.response.BaseResponse;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.TimesheetRepository;
import dev.codejar.repository.UserRepository;
import dev.codejar.service.TimesheetService;
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

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;


    @GetMapping("/all")
    public List<TimesheetDto> getAllTimesheet(){

        return timesheetService.timesheetList();
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Timesheet>> createTimesheet(@Valid @RequestBody Timesheet timesheet){

        Timesheet addTimesheet = timesheetService.insertTimesheet(timesheet);

        BaseResponse<Timesheet> response = new BaseResponse<>();
        response.setMessage("Succcess insert timesheet");
        response.setStatus(true);
        response.setData(addTimesheet);

        return ResponseEntity.ok(response);

    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Timesheet> editTimesheet(@PathVariable("id") Integer id,@Valid @RequestBody TimesheetDto timesheetDto){
        return timesheetService.editTimesheet(id, timesheetDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable("id") Integer id){
        return timesheetService.deleteTimesheet(id);
    }

    @GetMapping("/{id}")
    public Timesheet getTimesheetById(@PathVariable("id") Integer id){
        return timesheetService.findTimesheetById(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorHandler(MethodArgumentNotValidException e){

        var error = new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldError = ((FieldError) objectError).getField();
                    String messageError = objectError.getDefaultMessage();

                    error.put(fieldError, messageError);
                });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }





}
