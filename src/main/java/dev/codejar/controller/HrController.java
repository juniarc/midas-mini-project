package dev.codejar.controller;


import dev.codejar.model.dto.HrDashboardDto;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.projection.HrDashboardProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hr")
public class HrController {

    private final EmployeeRepository employeeRepository;




    @GetMapping({"", "/"})
    public String getHr(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFilter,
                        Model model) {

        List<HrDashboardProjection> data = employeeRepository.findEmployeeManagerWithTimesheet();

        List<HrDashboardDto> dashboard = new ArrayList<>();

        for (HrDashboardProjection item : data) {
            if (item.getSubmissionDate() != null) {
                if (dateFilter == null || item.getSubmissionDate().equals(dateFilter)) {
                    HrDashboardDto dto = new HrDashboardDto(
                            item.getEmployeeName(),
                            item.getManagerName(),
                            item.getSubmissionDate(),
                            item.getApprovalStatus()
                    );
                    dashboard.add(dto);
                }
            }
        }

        model.addAttribute("dashboard", dashboard);
        model.addAttribute("dateFilter", dateFilter);

        return "hr/index";
    }



}
