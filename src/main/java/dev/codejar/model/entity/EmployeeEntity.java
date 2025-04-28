package dev.codejar.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.results.internal.StandardEntityGraphTraversalStateImpl;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer id;

    @Column(name = "employee_name", insertable = false, updatable = false)
    private String employeeName;

    @Column(name = "date_of_join", nullable = false)
    private Date dateJoin;

    @Column(name = "manger_id")
    private Integer managerId;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "employee_email")
    private String employeeEmail;

}
