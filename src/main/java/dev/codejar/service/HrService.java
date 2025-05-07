package dev.codejar.service;


import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.projection.HrDashboardProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HrService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<HrDashboardProjection> hrView(){
        return employeeRepository.findEmployeeManagerWithTimesheet();
    }


}
