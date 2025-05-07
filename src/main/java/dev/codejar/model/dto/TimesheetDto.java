package dev.codejar.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class TimesheetDto {


//    @NotNull(message = "username cannot be null")
//    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "date cannot be null")
    private Date date;

    @NotNull(message = "task cannot be null")
    private String task;

    @NotNull(message = "hr cannot be null")
    private String hr;

    @NotNull(message = "status cannot be null")
    private String status;

    @NotNull(message = "remark cannot be null")
    private String remark;

    private String username;

//    @NotNull(message = "report manager cannot be null")
//    private String reportManager;
//
//    @NotNull(message = "report status cannot be null")
//    private String reportStatus;
//
//    @NotNull(message = "report remark cannot be null")
//    private String reportRemark;


}
