package dev.codejar.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.results.internal.StandardEntityGraphTraversalStateImpl;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "date_of_join", nullable = false)
    private Date dateJoin;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "employee_email")
    private String employeeEmail;

}
