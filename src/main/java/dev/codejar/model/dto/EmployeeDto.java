package dev.codejar.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class EmployeeDto {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Date cannot be null")
    private Date dateJoin;

    @NotNull(message = "Manager cannot be null")
    private Integer managerId;

    @NotNull(message = "Username cannot be null")
    private String employeeName;

    @NotNull(message = "Employee Email cannot be null")
    private String employeeEmail;

    @NotNull(message = "Employee Phone cannot be null")
    private String employeePhone;

}
