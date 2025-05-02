package dev.codejar.model.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "approval")
public class Approval {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String rm;
    private String rmStatus;
    private String rmRemark;





}
