package dev.codejar.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HrDashboardDto {

    private Integer id;

    private String employeeName;

    private String managerName;

    private Date submissionDate;

    private String approvalStatus;


}
