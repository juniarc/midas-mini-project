package dev.codejar.repository;


import dev.codejar.model.entity.Timesheet;
import dev.codejar.repository.projection.SubmissionStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

    @Query(value = "SELECT submit_date AS submissionDate, report_status AS approvalStatus FROM vw_submission_status", nativeQuery = true)
    List<SubmissionStatusProjection> findSubmissionStatus();



}
