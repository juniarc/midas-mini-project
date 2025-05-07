
package dev.codejar.controller;


import dev.codejar.model.dto.HrDashboardDto;
import dev.codejar.repository.EmployeeRepository;
import dev.codejar.repository.projection.HrDashboardProjection;
import dev.codejar.service.HrService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr")
public class HrController {

    @Autowired
    private HrService hrService;

    @GetMapping("/viewHR")
    public List<HrDashboardProjection> viewHr(){
        return hrService.hrView();
    }

}
