package dev.codejar.controller;


import dev.codejar.model.dto.HrDashboardDto;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.TimesheetRepository;
import dev.codejar.repository.projection.EmployeeManagerProjection;
import dev.codejar.repository.projection.SubmissionStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hr")
public class HrController {

    private final EmployeeRepository employeeRepository;

    private final TimesheetRepository timesheetRepository;


    @GetMapping({"", "/"})
    public String getHr(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFilter,
                        Model model) {

        List<EmployeeManagerProjection> employees = employeeRepository.findEmployeeAndManager();
        List<SubmissionStatusProjection> timesheets = timesheetRepository.findSubmissionStatus();

        List<HrDashboardDto> dashboard = new ArrayList<>();

        int size = Math.min(employees.size(), timesheets.size());
        for (int i = 0; i < size; i++) {
            SubmissionStatusProjection timesheet = timesheets.get(i);


            if (dateFilter == null || timesheet.getSubmissionDate().equals(dateFilter)) {
                HrDashboardDto dto = new HrDashboardDto(
                        employees.get(i).getEmployeeName(),
                        employees.get(i).getManagerName(),
                        timesheet.getSubmissionDate(),
                        timesheet.getApprovalStatus()
                );
                dashboard.add(dto);
            }
        }

        model.addAttribute("dashboard", dashboard);
        model.addAttribute("dateFilter", dateFilter);

        return "hr/index";
    }



}
