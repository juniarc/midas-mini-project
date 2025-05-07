package dev.codejar.service;

import dev.codejar.model.dto.TimesheetDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.model.entity.UserEntity;
import dev.codejar.repository.TimesheetRepository;
import dev.codejar.repository.UserRepository;
import dev.codejar.security.service.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimesheetService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    private final TimesheetRepository timesheetRepository;
    private final ModelMapper modelMapper;


    public TimesheetService(TimesheetRepository timesheetRepository, ModelMapper modelMapper) {
        this.timesheetRepository = timesheetRepository;
        this.modelMapper = modelMapper;
    }



    //GET AllTimesheet
    public List<TimesheetDto> timesheetList(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Timesheet> timesheets = timesheetRepository.findByUser(currentUser);

        return timesheets.stream().map(timesheet -> {
            TimesheetDto dto = new TimesheetDto();
            dto.setDate(timesheet.getDate());
            dto.setTask(timesheet.getTask());
            dto.setHr(String.valueOf(timesheet.getHr()));
            dto.setStatus(timesheet.getStatus());
            dto.setRemark(timesheet.getRemark());
            dto.setUsername(currentUser.getUsername()); // bisa langsung currentUser juga
            return dto;
        }).collect(Collectors.toList());
    }

    //ADD New Timesheet
    public Timesheet insertTimesheet(Timesheet timesheet){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        UserEntity currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        timesheet.setUser(currentUser);


        return timesheetRepository.save(timesheet);
    }


    // UPDATE New Timesheet
    public ResponseEntity<Timesheet> editTimesheet(Integer id, TimesheetDto timesheetDto){
        return timesheetRepository.findById(id)
                .map(timesheet -> {
                    timesheet.setDate(timesheetDto.getDate());
                    timesheet.setTask(timesheetDto.getTask());
                    timesheet.setHr(Integer.valueOf(timesheetDto.getHr()));
                    timesheet.setStatus(timesheetDto.getStatus());
                    timesheet.setRemark(timesheetDto.getRemark());


                    modelMapper.map(timesheet, TimesheetDto.class);
                    return ResponseEntity.ok(timesheet);
                }).orElse(ResponseEntity.notFound().build());
    }

    //GET ID
    public Optional<Timesheet> getID(@PathVariable("id") Integer id){
        return timesheetRepository.findById(id);
    }


    //DELETE Timesheet
    public ResponseEntity<Void> deleteTimesheet(Integer id){
        Optional<Timesheet> optional = timesheetRepository.findById(id);
        if (optional.isPresent()){
            timesheetRepository.delete(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }





}
