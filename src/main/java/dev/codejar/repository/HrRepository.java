package dev.codejar.repository;

import dev.codejar.model.entity.HrEntity;
import dev.codejar.repository.projection.HrDashboardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HrRepository extends JpaRepository<HrEntity, Integer> {


    @Query(value = """
    SELECT 
        e.employee_name as employeeName,
        m.employee_name as managerName,
        t.submit_date as submissionDate,
        t.report_status as approvalStatus
    FROM employees e
    LEFT JOIN employees m ON e.manger_id = m.emp_id
    LEFT JOIN timesheet t ON e.emp_id = t.id
""", nativeQuery = true)
    List<HrDashboardProjection> findEmployeeManagerWithTimesheet();

}