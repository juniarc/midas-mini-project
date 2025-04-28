package dev.codejar.repository;


import dev.codejar.model.entity.Timesheet;
import dev.codejar.repository.projection.SubmissionStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

//    Client findByProjectLead(String leadName);
//    Client findByProjectName(String name);



    @Query(value = "SELECT submission_date AS submissionDate, approval_status AS approvalStatus FROM vw_submission_status", nativeQuery = true)
    List<SubmissionStatusProjection> findSubmissionStatus();


//    @Query(value = "SELECT * FROM clients ORDER BY id", nativeQuery = true)
//    List<Client> findAllClientsNative();

}
