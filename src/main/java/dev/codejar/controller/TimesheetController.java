package dev.codejar.controller;


import dev.codejar.model.dto.TimesheetDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.model.entity.UserEntity;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.TimesheetRepository;
import dev.codejar.repository.UserRepository;
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
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"","/"})
    public String getClients(Model model){
        List<Timesheet> timesheetList = timesheetRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        for(Timesheet timesheet: timesheetList){
            Integer hrId = timesheet.getHr();
            if(hrId != null){
                userRepository.findById(hrId.longValue())
                        .map(UserEntity::getUsername)
                        .ifPresent(timesheet::setHrUsername);
            }

        }

        model.addAttribute("timesheet", timesheetList);
        System.out.println("Clients found: " + timesheetList.size());
        System.out.println("Employees foundL "+ employeeList.size());

        return "timesheet/index";
    }



    @GetMapping("/create")
    public String createTimesheet(Model model){
        TimesheetDto timesheetDto = new TimesheetDto();
        model.addAttribute("timesheetDto", timesheetDto);

        return "timesheet/create";
    }


    @PostMapping("/create")
    public String createTimesheet(@Valid @ModelAttribute TimesheetDto timesheetDto, BindingResult result){

        if (result.hasErrors()){
            return "timesheet/create";
        }

        // convert hr input from username to id
        Timesheet timesheet = getTimesheet(timesheetDto);
        Optional<UserEntity> user = userRepository.findByUsername(timesheetDto.getHr());
        Integer hrId = user.map(UserEntity::getId).map(Long::intValue).orElse(null);

        if(hrId != null){
            timesheet.setHr(hrId);
            timesheetRepository.save(timesheet);
            return "redirect:/timesheet";
        }

        return "/create";

    }



    private static Timesheet getTimesheet(TimesheetDto timesheetDto) {

        Timesheet timesheet = new Timesheet();

        timesheet.setUsername(timesheetDto.getUsername());
        timesheet.setDate(timesheetDto.getDate());
        timesheet.setTask(timesheetDto.getTask());
//        timesheet.setHr(timesheetDto.getHr());
        timesheet.setStatus(timesheetDto.getStatus());
        timesheet.setRemark(timesheetDto.getRemark());
        timesheet.setReportManager(timesheetDto.getReportManager());
        timesheet.setReportStatus(timesheetDto.getReportStatus());
        timesheet.setReportRemark(timesheetDto.getReportRemark());
        return timesheet;
    }


    @GetMapping("/edit")
    public String editTimesheet(Model model, @RequestParam int id){
        Timesheet timesheet = timesheetRepository.findById(id).orElse(null);
        if (timesheet == null){
            return "redirect:/timesheet";
        }

        TimesheetDto timesheetDto = new TimesheetDto();
        userRepository.findById(timesheet.getHr().longValue())
                .map(UserEntity::getUsername)
                .ifPresent(timesheetDto::setHr);
        timesheetDto.setUsername(timesheet.getUsername());
        timesheetDto.setDate(timesheet.getDate());
        timesheetDto.setTask(timesheet.getTask());
        timesheetDto.setStatus(timesheet.getStatus());
        timesheetDto.setRemark(timesheet.getRemark());
        timesheetDto.setReportManager(timesheet.getReportManager());
        timesheetDto.setReportStatus(timesheet.getReportStatus());
        timesheetDto.setReportRemark(timesheet.getReportRemark());

        model.addAttribute("timesheet", timesheet);
        model.addAttribute("timesheetDto", timesheetDto);

        return "timesheet/edit";

    }


    @PostMapping("/edit")
    public String editTimesheet(Model model, @RequestParam int id, @Valid @ModelAttribute TimesheetDto timesheetDto, BindingResult result) {

        Timesheet timesheet = timesheetRepository.findById(id).orElse(null);

        if (timesheet == null){
            return "redirect:/timesheet";
        }

        model.addAttribute("timesheet", timesheet);


        // update entity
        Optional<UserEntity> user = userRepository.findByUsername(timesheetDto.getHr());
        Integer hrId = user.map(UserEntity::getId).map(Long::intValue).orElse(null);

        timesheet.setUsername(timesheetDto.getUsername());
        timesheet.setTask(timesheetDto.getTask());
        timesheet.setStatus(timesheetDto.getStatus());
        timesheet.setRemark(timesheetDto.getRemark());
        timesheet.setReportManager(timesheetDto.getReportManager());
        timesheet.setReportStatus(timesheetDto.getReportStatus());
        timesheet.setReportRemark(timesheetDto.getReportRemark());


        if(hrId != null){
            timesheet.setHr(hrId);
            timesheetRepository.save(timesheet);
            return "redirect:/timesheet";

        }

        return "/edit";
    }


    @GetMapping("delete")
    public String deleteTimesheet(@RequestParam int id){
        Timesheet timesheet = timesheetRepository.findById(id).orElse(null);

        if (timesheet != null){
            timesheetRepository.delete(timesheet);
        }

        return "redirect:/timesheet";
    }




}
