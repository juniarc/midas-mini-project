package dev.codejar.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "submit_date", nullable = false)
    private Date date;

    @Column(name = "task")
    private String task;

    @Column(name = "hr")
    private Integer hr;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "remark")
    private String remark;

//    @Column(name = "report_manager")
//    private String reportManager;
//
//
    @Column(name = "report_status")
    private String reportStatus;
//
//    @Column(name = "report_remark")
//    private String reportRemark;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Transient
    private String hrUsername;


}
