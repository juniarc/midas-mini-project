package dev.codejar.service;

import dev.codejar.model.dto.TimesheetDto;
import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.repository.TimesheetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ModelMapper modelMapper;


    public TimesheetService(TimesheetRepository timesheetRepository, ModelMapper modelMapper) {
        this.timesheetRepository = timesheetRepository;
        this.modelMapper = modelMapper;
    }



    //GET AllTimesheet
    public List<Timesheet> timesheetList(){
        return timesheetRepository.findAll();
    }

    //ADD New Timesheet
    public Timesheet insertTimesheet(Timesheet timesheet){
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


    //EDIT Timesheet
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
