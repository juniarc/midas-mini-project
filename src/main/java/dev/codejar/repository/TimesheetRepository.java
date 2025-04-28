package dev.codejar.repository;


import dev.codejar.model.entity.Timesheet;
import dev.codejar.repository.projection.TimesheetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

//    Client findByProjectLead(String leadName);
//    Client findByProjectName(String name);



    @Query(
            value = "SELECT t.date AS submissionDate, m.report_status AS approvalStatus " +
                    "FROM timesheet t " +
                    "JOIN timesheet m ON t.report_manager = m.username",
            nativeQuery = true
    )
    List<TimesheetProjection> findDateAndStatus();


//    @Query(value = "SELECT * FROM clients ORDER BY id", nativeQuery = true)
//    List<Client> findAllClientsNative();

}
